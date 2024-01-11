package com.pdpuniversity.reservationsystem.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Item {
    private Long id;
    private String name;
    private Float price;
    private String description;
}
