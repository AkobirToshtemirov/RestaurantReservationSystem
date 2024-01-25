package com.pdpuniversity.reservationsystem.entity;

import lombok.*;

/**
 * The {@code MenuItem} class represents a menu item with a name, price, and list of ingredients.
 * It provides methods to initialize, update, and retrieve information about a menu item.
 *
 * @author Akobir Toshtemirov
 * @version 1.0
 * @since 2024-01-25
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class MenuItem {
    private String name;
    private double price;
    private String ingredients;


    /**
     * Returns a string representation of the menu item.
     *
     * @return A string containing the name, price, and ingredients of the menu item.
     */
    @Override
    public String toString() {
        return name + ": $" + price + "\nIngredients: " + ingredients + "\n";
    }
}
