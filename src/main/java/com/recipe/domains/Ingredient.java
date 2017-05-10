package com.recipe.domains;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", length = 16)
    private UUID id;

    public Ingredient() {

    }

    public Ingredient(Recipe recipe, double quantity, String name, String unit) {
        this.recipe = recipe;
        this.quantity = quantity;
        this.name = name;
        this.unit = unit;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Recipe recipe;


    @NotNull
    @Column(name = "quantity")
    private double quantity;

    @NotEmpty
    @Length(max = 500)
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Length(max = 100)
    @Column(name = "unit")
    private String unit;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}