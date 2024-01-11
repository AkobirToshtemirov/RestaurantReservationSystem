package com.pdpuniversity.reservationsystem.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private List<Item> items;
}
