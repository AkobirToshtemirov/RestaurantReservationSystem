package com.pdpuniversity.reservationsystem.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ReservationManager} class manages reservations, including creation, updating,
 * deletion, and retrieval of reservations. It ensures that reservations do not overlap in time.
 *
 * @author Akobir Toshtemirov
 * @version 1.0
 * @since 2024-01-25
 */
@Getter
public class ReservationManager {
    /**
     * A list to store all reservations managed by the ReservationManager.
     */
    private List<Reservation> reservations;

    /**
     * Constructs a ReservationManager with an empty list of reservations.
     */
    public ReservationManager() {
        this.reservations = new ArrayList<>();
    }

    /**
     * Creates a new reservation with the provided details and adds it to the list of reservations.
     *
     * @param customerName   The name of the customer making the reservation.
     * @param date           The date of the reservation.
     * @param time           The time of the reservation.
     * @param numberOfGuests The number of guests for the reservation.
     * @param menu           The menu associated with the reservation.
     * @return The created reservation if successful, or null if overlapping with existing reservations.
     */
    public Reservation createReservation(String customerName, String date, String time, int numberOfGuests, Menu menu) {
        if (!isOverlappingReservation(date, time)) {
            Reservation reservation = new Reservation(customerName, date, time, numberOfGuests, menu);
            reservations.add(reservation);
            System.out.println("Reservation created successfully. ID: " + reservation.getId());
            return reservation;
        } else {
            System.out.println("Overlapping reservation. Please choose a different date/time.");
            return null;
        }
    }

    /**
     * Checks if a new reservation overlaps with existing reservations based on date and time.
     *
     * @param date The date of the new reservation.
     * @param time The time of the new reservation.
     * @return {@code true} if there is an overlap, {@code false} otherwise.
     */
    private boolean isOverlappingReservation(String date, String time) {
        for (Reservation existingReservation : reservations) {
            if (existingReservation.getDate().equals(date) && existingReservation.getTime().equals(time)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates an existing reservation with new date, time, and number of guests.
     *
     * @param reservationId     The ID of the reservation to be updated.
     * @param newDate           The new date for the reservation.
     * @param newTime           The new time for the reservation.
     * @param newNumberOfGuests The new number of guests for the reservation.
     */
    public void updateReservation(int reservationId, String newDate, String newTime, int newNumberOfGuests) {
        Reservation reservationToUpdate = getReservationById(reservationId);
        if (reservationToUpdate != null) {
            if (!isOverlappingReservation(newDate, newTime)) {
                reservationToUpdate.setDate(newDate);
                reservationToUpdate.setTime(newTime);
                reservationToUpdate.setNumberOfGuests(newNumberOfGuests);
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("Overlapping reservation. Update failed.");
            }
        } else {
            System.out.println("Reservation not found. Update failed.");
        }
    }

    /**
     * Deletes a reservation with the specified ID.
     *
     * @param reservationId The ID of the reservation to be deleted.
     */
    public void deleteReservation(int reservationId) {
        Reservation reservationToRemove = getReservationById(reservationId);
        if (reservationToRemove != null) {
            reservations.remove(reservationToRemove);
            System.out.println("Reservation deleted successfully.");
        } else {
            System.out.println("Reservation not found. Deletion failed.");
        }
    }

    /**
     * Retrieves a reservation by its unique ID.
     *
     * @param reservationId The ID of the reservation to be retrieved.
     * @return The reservation with the specified ID, or null if not found.
     */
    public Reservation getReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }


    /**
     * Retrieves a list of all reservations managed by the ReservationManager.
     *
     * @return A list of all reservations.
     */
    public List<Reservation> getAllReservations() {
        return reservations;
    }

    /**
     * Searches and retrieves reservations based on the customer's name.
     *
     * @param customerName The name of the customer for whom reservations are searched.
     * @return A list of reservations matching the customer's name.
     */
    public List<Reservation> searchReservationsByCustomerName(String customerName) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerName().equalsIgnoreCase(customerName)) {
                result.add(reservation);
            }
        }
        return result;
    }
}