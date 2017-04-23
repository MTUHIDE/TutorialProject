package com.recipe.controllers;

import com.recipe.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created by dough on 4/9/2017.
 */
@Controller
public class IndexController {

    @Value("${com.recipe.recipeApplication.applicationName}")
    String applicationName;

    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * The controller method for returning the index page. Responds to the following
     * HTTP GET requestions (examples based on hosting from development machine):
     * <p>
     * 'localhost:8080'
     * or
     * 'localhost:8080/home'
     *
     * @param model The Spring model for this page.
     * @return The path to the view template relative ot the /templates directory.
     */
    @RequestMapping(path = {"/", "/home"}, method = RequestMethod.GET)
    public String renderIndex(Model model, HttpServletRequest httpServletRequest) {
        LoggerUtils.logRemoteAddr("indexPage", logger, httpServletRequest);
        model.addAttribute("applicationName", applicationName);
        return "index";
    }

}
