package com.pdpuniversity.reservationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Menu {
    private Map<String, MenuItem> menuItems;

    public Menu() {
        this.menuItems = new HashMap<>();
    }

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

    public void addMenuItem(String name, double price, String ingredients) {
        if (menuItems.size() < 10) {
            MenuItem newItem = new MenuItem(name, price, ingredients);
            menuItems.put(name, newItem);
        } else {
            System.out.println("Menu is full. Cannot add more items.");
        }
    }

    public void updateMenuItem(String name, double price, String ingredients) {
        if (menuItems.containsKey(name)) {
            MenuItem item = menuItems.get(name);
            item.setPrice(price);
            item.setIngredients(ingredients);
        } else {
            System.out.println("Item not found in the menu.");
        }
    }

    public void removeMenuItem(String name) {
        menuItems.remove(name);
    }

    public void printMenu() {
        for (MenuItem item : menuItems.values()) {
            System.out.println(item);
        }
    }

    public MenuItem search(String name) {
        return menuItems.get(name);
    }
}
