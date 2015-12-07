package org.currconv.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.currconv.entities.app.Locations;
import org.currconv.entities.user.User;
import org.currconv.entities.user.UserLogin;
import org.currconv.services.UserService;
import org.currconv.web.controller.validator.UserValidator;
import org.currconv.web.controller.validator.UserLoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 

/**
 *  Controller for user handling.
 */
@Controller
public class UserController {

    private Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest context;

    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;

    @Autowired
    @Qualifier("userLoginValidator")
    private UserLoginValidator userLoginValidator;

    @InitBinder("user")
    protected void initUserBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10)); 
        binder.setValidator(userValidator);
    }

    @InitBinder("userLogin")
    protected void initUserLoginBinder(WebDataBinder binder) {
        binder.setValidator(userLoginValidator);
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
        String returnVal = "redirect:.";
        try{
            context.logout();
        } catch (Exception ex) {
            log.error("Exception in logout", ex);
        }
        return returnVal;
	}

    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
        ModelAndView mav = new ModelAndView("userViews/login");
        UserLogin userLogin = new UserLogin();
        mav.getModelMap().put("userLogin", userLogin);
        return mav;
	}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @Validated UserLogin userLogin, BindingResult bindingResult) {
        String returnVal = "redirect:.";
        if(bindingResult.hasErrors()) {
            userLogin.setPassword("");
            model.addAttribute("userLogin", userLogin);
            returnVal = "userViews/login";
        } else {
            try{
                context.login(userLogin.getUsername(), userLogin.getPassword());
            } catch (Exception ex) {
                if(ex.getMessage().equals("Bad credentials")){
                    userLogin.setPassword("");
                    model.addAttribute("userLogin", userLogin);
                    returnVal = "userViews/login";
                    bindingResult.rejectValue("password", "validation.invalidCredentials");
                } else {
                    log.error("Exception in login", ex);
                }
            }
        }
        return returnVal;
	}

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public ModelAndView signIn() {
        ModelAndView mav = new ModelAndView("userViews/signIn");
        User newUser = new User();
        mav.getModelMap().put("user", newUser);
        mav.getModelMap().put("countries", Locations.getCountries());
        return mav;
	}

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public String signIn(Model model, @Validated User user, BindingResult bindingResult) {
        String returnVal = "redirect:.";
        if(bindingResult.hasErrors()) {
            user.setPassword("");
            user.setPasswordVer("");
            model.addAttribute("user", user);
            model.addAttribute("countries", Locations.getCountries());
            returnVal = "userViews/signIn";
        } else {
            userService.saveUser(user);
            log.info("User \""+user.getUsername()+"\" created. Id: "+user.getUserId());
            try{
                context.login(user.getUsername(), user.getPassword());
            } catch (Exception ex) {
                log.error("Exception in signIn", ex);
            }
        }
        return returnVal;
	}

}