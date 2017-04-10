package com.recipe.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ingredient_id", length = 16)
    private UUID ingredientID;

    public Ingredient(Recipe recipe, double ingredientQuantity, String ingredientName, String ingredientUnit) {
        this.recipe = recipe;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientName = ingredientName;
        this.ingredientUnit = ingredientUnit;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Recipe recipe;

    @Column(name = "ingredient_quantity")
    private double ingredientQuantity;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "ingredient_unit")
    private String ingredientUnit;


    public UUID getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(UUID ingredientID) {
        this.ingredientID = ingredientID;
    }


    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    public double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientUnit() {
        return ingredientUnit;
    }

    public void setIngredientUnit(String ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
    }
}