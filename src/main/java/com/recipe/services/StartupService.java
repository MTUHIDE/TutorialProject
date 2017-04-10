package com.recipe.services;

import com.recipe.domain.Ingredient;
import com.recipe.domain.Recipe;
import com.recipe.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dough on 4/9/2017.
 */
@Service
public class StartupService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public StartupService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Scheduled(fixedRate = Long.MAX_VALUE)
    private void insertData() {

        ArrayList<Recipe> dummyRecipes = new ArrayList<>();

        /* Create a new recipe. Notice how we set both sides of the relationship, i.e. we set
           an ingredient's recipe and a recipe's ingredients. This is required for correct 2-way mapping by
           Hibernate.
         */
        ArrayList<Ingredient> soupIngredients = new ArrayList<>();
        Recipe chickenSoup = new Recipe(new Date(), "Chicken Soup", "A yummy chicken soup.", "Soups");
        soupIngredients.add(new Ingredient(chickenSoup, 1.0, "Noodles", "Package"));
        soupIngredients.add(new Ingredient(chickenSoup, 1.0, "Chicken Broth", "Cup"));
        chickenSoup.setIngredients(soupIngredients);
        dummyRecipes.add(chickenSoup);

        recipeRepository.save(dummyRecipes);
    }

}
