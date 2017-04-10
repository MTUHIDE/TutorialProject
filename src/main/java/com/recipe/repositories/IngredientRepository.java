package com.recipe.repositories;

import com.recipe.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by dough on 4/9/2017.
 */
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
}
