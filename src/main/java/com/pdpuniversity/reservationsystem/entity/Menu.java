package com.pdpuniversity.reservationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Menu} class represents a menu containing various food items.
 * It allows the addition, modification, and removal of menu items, as well as
 * searching for items by name. The default menu can also be initialized.
 *
 * @author Akobir Toshtemirov
 * @version 1.0
 * @since 2024-01-25
 */
@Getter
@Setter
@AllArgsConstructor
public class Menu {
    /**
     * A map containing menu items with their names as keys.
     */
    private Map<String, MenuItem> menuItems;

    /**
     * Constructs an empty menu.
     */
    public Menu() {
        this.menuItems = new HashMap<>();
    }

    /**
     * Initializes the default menu with pre-defined items.
     */
    public void initializeDefaultMenu() {
        addMenuItem("Manti", 10.0, "meat, onion");
        addMenuItem("Lagman", 11.0, "noodles, vegetables, meat");
        addMenuItem("Shashlik", 15.0, "marinated meat skewers");
        addMenuItem("Plov", 13.5, "rice, meat, vegetables");
        addMenuItem("Samsa", 8.0, "pastry filled with meat and onions");
        addMenuItem("Borscht", 9.5, "beet soup with vegetables and meat");
        addMenuItem("Dolma", 14.0, "grape leaves stuffed with rice and meat");
        addMenuItem("Kebab", 14.5, "grilled meat on skewers");
        addMenuItem("Salmon Teriyaki", 16.0, "grilled salmon with teriyaki sauce");
        addMenuItem("Chicken Alfredo", 13.0, "grilled chicken with creamy Alfredo sauce");
    }

    /**
     * Adds a new menu item to the menu.
     *
     * @param name        The name of the menu item.
     * @param price       The price of the menu item.
     * @param ingredients The ingredients of the menu item.
     */
    public void addMenuItem(String name, double price, String ingredients) {
        if (menuItems.size() < 10) {
            MenuItem newItem = new MenuItem(name, price, ingredients);
            menuItems.put(name, newItem);
        } else {
            System.out.println("Menu is full. Cannot add more items.");
        }
    }

    /**
     * Updates an existing menu item with new information.
     *
     * @param name        The name of the menu item to be updated.
     * @param price       The new price of the menu item.
     * @param ingredients The new ingredients of the menu item.
     */
    public void updateMenuItem(String name, double price, String ingredients) {
        if (menuItems.containsKey(name)) {
            MenuItem item = menuItems.get(name);
            item.setPrice(price);
            item.setIngredients(ingredients);
        } else {
            System.out.println("Item not found in the menu.");
        }
    }

    /**
     * Removes a menu item from the menu.
     *
     * @param name The name of the menu item to be removed.
     */
    public void removeMenuItem(String name) {
        menuItems.remove(name);
    }

    /**
     * Prints all menu items to the console.
     */
    public void printMenu() {
        for (MenuItem item : menuItems.values()) {
            System.out.println(item);
        }
    }

    /**
     * Searches for a menu item by its name.
     *
     * @param name The name of the menu item to search for.
     * @return The {@code MenuItem} object if found, otherwise {@code null}.
     */
    public MenuItem search(String name) {
        return menuItems.get(name);
    }
}
