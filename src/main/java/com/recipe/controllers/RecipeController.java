package com.recipe.controllers;

import com.recipe.domains.Ingredient;
import com.recipe.domains.Recipe;
import com.recipe.repositories.RecipeRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @GetMapping("/create")
    public String serveForm(Model model) {

        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "create";
    }

    @PostMapping(value = "/create", params = {"addRow"})
    public String addRow(@ModelAttribute("recipe") Recipe recipe, final BindingResult bindingResult) {

        if (recipe.getIngredients() == null) {
            recipe.setIngredients(new ArrayList<>());
        }

        recipe.getIngredients().add(new Ingredient());
        return "create";
    }

    @PostMapping(value = "/create", params = {"removeRow"})
    public String removeRow(@ModelAttribute("recipe") Recipe recipe,
                            final BindingResult bindingResult,
                            HttpServletRequest request) {
        Integer rowId = Integer.valueOf(request.getParameter("removeRow"));
        recipe.getIngredients().remove(rowId.intValue());
        return "create";
    }

    @PostMapping("/create")
    public String saveRecipe(@ModelAttribute("recipe") @Valid Recipe recipe, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "create";
        }

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe);
        }

        recipeRepository.save(recipe);
        return "redirect:/recipes";
    }
}
