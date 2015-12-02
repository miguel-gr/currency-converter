package org.currconv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewsController {
    
    //@Autowired
    //private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Hello!");
		return "index";

	}

	@RequestMapping(value = "/index/{name:.+}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("msg", name);

		return model;

	}

	@RequestMapping(value = "/internalViews/{viewName:.+}", method = RequestMethod.GET)
	public ModelAndView internalViews(@PathVariable("viewName") String viewName) {

		ModelAndView model = new ModelAndView();
		model.setViewName("internalViews/"+viewName);
		model.addObject("msg", viewName);

		return model;

	}

}