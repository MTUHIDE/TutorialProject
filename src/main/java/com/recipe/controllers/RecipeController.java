package com.recipe.controllers;

import com.recipe.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * Created by dough on 4/15/2017.
 */
@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes")
    public String displayRecipes(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes";
    }

    @GetMapping("/recipe/{recipeId}")
    public String displayRecipe(Model model, @PathVariable("recipeId") UUID recipeId) {
        model.addAttribute("recipe", recipeRepository.findOne(recipeId));
        //Serve the /templates/recipe.html template.
        return "recipe";
    }
}
