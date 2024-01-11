package com.pdpuniversity.reservationsystem.service.impl;

import com.pdpuniversity.reservationsystem.db.AppDB;
import com.pdpuniversity.reservationsystem.entity.Item;
import com.pdpuniversity.reservationsystem.entity.Menu;
import com.pdpuniversity.reservationsystem.service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuServiceImpl implements MenuService {
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public String displayMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu \n");

        for (Item item : AppDB.menu.getItems()) {
            sb.append(counter.incrementAndGet() + ". ");
            sb.append("Item name: " + item.getName() + " | ");
            sb.append("Item price: " + item.getPrice() + " | ");
            sb.append("Item description: " + item.getDescription());
            sb.append("\n");
        }
        counter.set(0);
        return sb.toString();
    }

    @Override
    public Item addItem(Item item) {
        Menu menu = AppDB.menu;
        List<Item> items = menu.getItems();
        items.add(item);
        menu.setItems(items);
        return item;
    }

    @Override
    public void removeItem(Long itemId) {
        Menu menu = AppDB.menu;
        List<Item> items = menu.getItems();

        for (Item item : items) {
            if (Objects.equals(item.getId(), itemId)) {
                items.remove(item);
                System.out.println("Item removed with id: " + itemId);
                return;
            }
        }

        System.out.println("Item not found with id: " + itemId);
    }

    @Override
    public Item updateItem(Long itemId, Item updatedItem) {
        Menu menu = AppDB.menu;
        List<Item> items = menu.getItems();
        Item existingItem = null;
        for (Item item : items) {
            if (Objects.equals(item.getId(), itemId)) {
                existingItem = item;
            }
        }
        if (existingItem == null) {
            System.out.println("Item not found with it: " + itemId);
            return null;
        }
        existingItem.setName(updatedItem.getName());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setDescription(updatedItem.getDescription());

        return existingItem;
    }

    @Override
    public List<Item> searchItems(String searchWord) {
        List<Item> searchResult = new ArrayList<>();

        for (Item item : AppDB.menu.getItems()) {
            if (item.getName().contains(searchWord) || item.getDescription().contains(searchWord)) {
                searchResult.add(item);
            }
        }

        return searchResult;
    }
}
