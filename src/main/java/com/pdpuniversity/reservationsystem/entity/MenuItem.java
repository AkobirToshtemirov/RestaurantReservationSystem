package com.pdpuniversity.reservationsystem.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MenuItem {
    private String name;
    private double price;
    private String ingredients;

    public MenuItem(String name, double price, String ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return name + ": $" + price + "\nIngredients: " + ingredients;
    }
}
