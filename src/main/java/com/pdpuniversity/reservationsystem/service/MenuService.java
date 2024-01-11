package com.pdpuniversity.reservationsystem.service;

import com.pdpuniversity.reservationsystem.entity.Item;

import java.util.List;

public interface MenuService {
    String displayMenu();

    Item addItem(Item item);

    void removeItem(Long itemId);

    Item updateItem(Long itemId, Item updatedItem);

    List<Item> searchItems(String searchWord);
}
