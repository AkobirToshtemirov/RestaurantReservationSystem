package com.pdpuniversity.reservationsystem.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ReservationManager {
    private List<Reservation> reservations;

    public ReservationManager() {
        this.reservations = new ArrayList<>();
    }

    public Reservation createReservation(String customerName, String date, String time, int numberOfGuests, Menu menu) {
        if (!isOverlappingReservation(date, time)) {
            Reservation reservation = new Reservation(customerName, date, time, numberOfGuests, menu);
            reservations.add(reservation);
            System.out.println("Reservation created successfully. ID: " + reservation.getId());
            return reservation; // Return the created reservation
        } else {
            System.out.println("Overlapping reservation. Please choose a different date/time.");
            return null; // Return null if reservation creation failed
        }
    }

    private boolean isOverlappingReservation(String date, String time) {
        for (Reservation existingReservation : reservations) {
            if (existingReservation.getDate().equals(date) && existingReservation.getTime().equals(time)) {
                return true;
            }
        }
        return false;
    }

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

    public void deleteReservation(int reservationId) {
        Reservation reservationToRemove = getReservationById(reservationId);
        if (reservationToRemove != null) {
            reservations.remove(reservationToRemove);
            System.out.println("Reservation deleted successfully.");
        } else {
            System.out.println("Reservation not found. Deletion failed.");
        }
    }

    public Reservation getReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

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