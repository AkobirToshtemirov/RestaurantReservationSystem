package com.pdpuniversity.reservationsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Reservation} class represents a reservation with customer details,
 * reservation information, meal choices, and total cost calculation.
 *
 * @author Akobir Toshtemirov
 * @version 1.0
 * @since 2024-01-25
 */
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    /**
     * A static variable to generate unique IDs for reservations.
     */
    private static int nextId = 1;

    private int id;
    private String customerName;
    private String date;
    private String time;
    private int numberOfGuests;
    private Menu menu;
    private List<String> mealChoices;

    /**
     * The maximum number of meal choices allowed for a reservation.
     */
    private static final int MAX_MEAL_CHOICES = 10;

    /**
     * Constructs a reservation with default values.
     *
     * @param customerName   The name of the customer.
     * @param date           The date of the reservation.
     * @param time           The time of the reservation.
     * @param numberOfGuests The number of guests for the reservation.
     * @param menu           The menu associated with the reservation.
     */
    public Reservation(String customerName, String date, String time, int numberOfGuests, Menu menu) {
        this.id = nextId++;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.numberOfGuests = numberOfGuests;
        this.menu = menu;
        this.mealChoices = new ArrayList<>();
    }

    /**
     * Adds a meal choice to the reservation.
     *
     * @param itemName The name of the menu item to be added as a meal choice.
     */
    public void addMeal(String itemName) {
        if (mealChoices.size() < MAX_MEAL_CHOICES) {
            MenuItem item = menu.search(itemName);
            if (item != null) {
                mealChoices.add(itemName);
            } else {
                System.out.println("Item not found in the menu.");
            }
        } else {
            System.out.println("Cannot add more than " + MAX_MEAL_CHOICES + " meal choices.");
        }
    }

    /**
     * Removes a meal choice from the reservation.
     *
     * @param itemName The name of the menu item to be removed from meal choices.
     */
    public void removeMeal(String itemName) {
        mealChoices.remove(itemName);
    }

    /**
     * Calculates the total cost of the reservation based on selected meal choices.
     *
     * @return The total cost of the reservation.
     */
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


    /**
     * Generates a string representation of the reservation, including details
     * such as customer information, reservation time, meal choices, and total cost.
     *
     * @return A string containing reservation details.
     */
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
