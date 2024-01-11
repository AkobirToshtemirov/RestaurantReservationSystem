package com.pdpuniversity.reservationsystem;

import com.pdpuniversity.reservationsystem.db.AppDB;
import com.pdpuniversity.reservationsystem.entity.Item;
import com.pdpuniversity.reservationsystem.service.MenuService;
import com.pdpuniversity.reservationsystem.service.impl.MenuServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    private static final AtomicLong counter = new AtomicLong(0);

    static {
        List<Item> items = new ArrayList<>();

        items.add(new Item(counter.incrementAndGet(), "Item 1", 12.5F, "Descirption for Item 1"));
        items.add(new Item(counter.incrementAndGet(), "Item 2", 22.5F, "Descirption for Item 2"));
        items.add(new Item(counter.incrementAndGet(), "Item 3", 112.5F, "Descirption for Item 3"));

        AppDB.menu.setItems(items);
    }

    public static void main(String[] args) {
        MenuService menuService = new MenuServiceImpl();

        menuService.addItem(new Item(counter.incrementAndGet(), "Adding Item", 100F, "Description for adding item"));

        System.out.println(menuService.displayMenu());

        menuService.removeItem(4L);

        System.out.println(menuService.displayMenu());
    }
}