package com.recipe.controllers;

import com.recipe.domains.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by dough on 5/11/2017.
 */

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String renderRegistration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


}
