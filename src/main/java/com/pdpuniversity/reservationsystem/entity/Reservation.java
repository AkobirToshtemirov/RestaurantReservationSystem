package com.pdpuniversity.reservationsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    private static int nextId = 1;

    private int id;
    private String customerName;
    private String date;
    private String time;
    private int numberOfGuests;
    private Menu menu;
    private List<String> mealChoices;

    public Reservation(String customerName, String date, String time, int numberOfGuests, Menu menu) {
        this.id = nextId++;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.numberOfGuests = numberOfGuests;
        this.menu = menu;
        this.mealChoices = new ArrayList<>();
    }

    public void addMeal(String itemName) {
        if (mealChoices.size() < 10) {
            MenuItem item = menu.search(itemName);
            if (item != null) {
                mealChoices.add(itemName);
            } else {
                System.out.println("Item not found in the menu.");
            }
        } else {
            System.out.println("Cannot add more than 10 meal choices.");
        }
    }

    public void removeMeal(String itemName) {
        mealChoices.remove(itemName);
    }

    public double calculateTotalCost() {
        double total = 0.0;
        for (String itemName : mealChoices) {
            MenuItem item = menu.search(itemName);
            if (item != null) {
                total += item.getPrice();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder reservationDetails = new StringBuilder();
        reservationDetails.append("Reservation ID: ").append(id)
                .append("\nReservation for ").append(customerName)
                .append(" on ").append(date).append(" at ").append(time)
                .append(" for ").append(numberOfGuests).append(" guests\n");

        for (String itemName : mealChoices) {
            MenuItem item = menu.search(itemName);
            if (item != null) {
                reservationDetails.append(item).append("\n");
            }
        }

        reservationDetails.append("Total: $").append(calculateTotalCost());

        return reservationDetails.toString();
    }
}
