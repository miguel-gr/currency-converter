package org.currconv.web.controller;

import java.util.List;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.currconv.entities.user.User;
import org.currconv.entities.currencies.CurrencyConversion;
import org.currconv.entities.currencies.CurrencyConversionForm;
import org.currconv.web.controller.validator.CurrencyConversionFormValidator;
import org.currconv.entities.currencies.Currencies;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.currconv.entities.user.UserTypes;
import org.currconv.services.CurrencyConverterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.currconv.services.UserService; 
import org.springframework.web.bind.WebDataBinder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.InitBinder;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 *  Controller for general application functionality.
 */
@Controller
public class ViewsController {

    private Log log = LogFactory.getLog(ViewsController.class);

    @Autowired
    @Qualifier("currencyConversionFormValidator")
    private CurrencyConversionFormValidator currencyConversionFormValidator;

    @InitBinder("currencyConversionForm")
    protected void initCurrencyConversionFormBinder(WebDataBinder binder) {
        binder.setValidator(currencyConversionFormValidator);
    }

    @Autowired
    private CurrencyConverterService currencyConverterService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest context;

	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap model, Principal principal) {
        if (context.isUserInRole(UserTypes.USER.name())) {
            String userName = principal.getName();
            model.addAttribute("username", userName);
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("message", "Hello");
        }
		return "index";
	}*/

    /**
     * General informational pages
     */
	@RequestMapping(value = "/internalViews/{viewName:.+}", method = RequestMethod.GET)
	public ModelAndView internalViews(@PathVariable("viewName") String viewName) {
		ModelAndView model = new ModelAndView();
        if (!validateAndInitializeLoggedUser(model, "internalViews/"+viewName+"User", "")) {
            model.setViewName("internalViews/"+viewName);
        }
		return model;
	}

    /**
     * Currency rates pages
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView currencyRates(final Principal principal) {
		ModelAndView model = new ModelAndView();
		if (validateAndInitializeLoggedUser(model, "currencyRates/logged", "")){
            User user = this.getUser(principal);
            List<CurrencyConversion> recentConversions = currencyConverterService.findRecentConversions(5, user.getUserId());
            CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
            model.addObject("currencyConversionForm", currencyConversionForm);
            model.addObject("recentConversions", recentConversions);
            model.addObject("currencies", Currencies.getCurrencies());
            
        } else {
            model.setViewName("currencyRates/notLogged");
        }
		return model;
	}

    /**
     * Currency rates pages
     */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView currencyRates(ModelAndView model, @Validated CurrencyConversionForm currencyConversionForm, BindingResult bindingResult, Principal principal) {
		if (validateAndInitializeLoggedUser(model, "currencyRates/logged", "")){
            User user = this.getUser(principal);
            model.addObject("currencies", Currencies.getCurrencies());
            model.addObject("currencyConversionForm", currencyConversionForm);
            List<CurrencyConversion> recentConversions = currencyConverterService.findRecentConversions(5, user.getUserId());
            model.addObject("recentConversions", recentConversions);
            if(bindingResult.hasErrors()) {
                
            } else {
                Money baseMoney = Money.parse(currencyConversionForm.getOriginalCode()+" "+currencyConversionForm.getOriginalValue());
                CurrencyUnit destinyCurrency = CurrencyUnit.getInstance(currencyConversionForm.getConvertedCode());
                CurrencyConversion conversion = currencyConverterService.getCurrencyConversion(baseMoney, destinyCurrency);
                model.addObject("conversion", conversion);
                conversion.setUserId(user.getUserId());
                currencyConverterService.saveCurrencyConversion(conversion);
            }
        } else {
            log.info("POST request from an user not logged.");
            model.setViewName("currencyRates/notLogged");
        }
		return model;
	}

    private boolean validateAndInitializeLoggedUser(final ModelAndView model, final String viewName, final String message){
        if (context.isUserInRole(UserTypes.USER.name())) {
            model.setViewName(viewName);
            model.addObject("isLoggedIn", true);
            model.addObject("message", message);
            return true;
        }
        return false;
    }

    /**
     * Get the user data.
     * TODO when only the user id is needed it could be added to an implementaton of java.security.Principal.
     */
    private User getUser(final Principal principal){
        return userService.findByUserName(principal.getName());
    }

}
