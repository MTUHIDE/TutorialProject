package com.recipe.services;

import com.recipe.domains.Ingredient;
import com.recipe.domains.Recipe;
import com.recipe.domains.User;
import com.recipe.repositories.RecipeRepository;
import com.recipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dough on 4/9/2017.
 */
@Service
public class StartupService {

    private final UserRepository userRepository;

    @Autowired
    public StartupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRate = Long.MAX_VALUE)
    private void insertData() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        ArrayList<Recipe> dummyRecipes = new ArrayList<>();

        User testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword(bCryptPasswordEncoder.encode("password"));

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

        //Link to the user.
        chickenSoup.setUser(testUser);
        testUser.setRecipes(dummyRecipes);

        userRepository.save(testUser);
    }

}
