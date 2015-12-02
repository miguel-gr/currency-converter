package org.currconv.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.currconv.entities.app.Locations;
import org.currconv.entities.user.User;
import org.currconv.services.UserService;
import org.currconv.web.controller.validator.UserValidator;
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
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10)); 
        binder.setValidator(userValidator);
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
        String returnVal = "index";
        if(bindingResult.hasErrors()) {
            user.setPassword("");
            user.setPasswordVer("");
            model.addAttribute("user", user);
            model.addAttribute("countries", Locations.getCountries());
            returnVal = "userViews/signIn";
        } else {
            userService.saveUser(user);
        }
        System.out.println(returnVal);
        return returnVal;
	}

}