package com.pdpuniversity.reservationsystem;

import com.pdpuniversity.reservationsystem.entity.Menu;
import com.pdpuniversity.reservationsystem.entity.MenuItem;
import com.pdpuniversity.reservationsystem.entity.Reservation;
import com.pdpuniversity.reservationsystem.entity.ReservationManager;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Menu menu = new Menu();
    private static final ReservationManager reservationManager = new ReservationManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menu.initializeDefaultMenu();
        int userTypeChoice;

        do {
            System.out.println("===== Main Menu =====");
            System.out.println("1. Staff Interface");
            System.out.println("2. Customer Interface");
            System.out.println("0. Exit");

            try {
                System.out.print("Enter your choice: ");
                userTypeChoice = scanner.nextInt();
                scanner.nextLine();

                switch (userTypeChoice) {
                    case 1:
                        runStaffInterface(scanner);
                        break;

                    case 2:
                        runCustomerInterface(scanner);
                        break;

                    case 0:
                        System.out.println("Exiting the Reservation System.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                userTypeChoice = -1;
            }
        } while (userTypeChoice != 0);

        scanner.close();
    }

    private static void runStaffInterface(Scanner scanner) {
        int staffChoice;

        while (true) {
            System.out.println("===== Staff Interface =====");
            System.out.println("1. Add MenuItem");
            System.out.println("2. Edit Menu");
            System.out.println("3. Delete Menu");
            System.out.println("4. View Menu");
            System.out.println("5. Search menu item by name/id");
            System.out.println("6. Get All Reservations");
            System.out.println("7. Search Reservations by Customer Name");
            System.out.println("8. Go back to Main Menu");
            System.out.println("0. Exit");

            try {
                System.out.print("Enter your choice: ");
                staffChoice = scanner.nextInt();
                scanner.nextLine();

                switch (staffChoice) {
                    case 1:
                        System.out.print("Enter item name: ");
                        String itemName = scanner.nextLine();
                        System.out.print("Enter item price: ");

                        try {
                            double itemPrice = Double.parseDouble(scanner.nextLine());
                            System.out.print("Enter item ingredients: ");
                            String itemIngredients = scanner.nextLine();

                            menu.addMenuItem(itemName, itemPrice, itemIngredients);
                            System.out.println("MenuItem added successfully.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid price.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter item name to edit: ");
                        String editItemName = scanner.nextLine();
                        System.out.print("Enter new price: ");

                        try {
                            double newPrice = Double.parseDouble(scanner.nextLine());
                            System.out.print("Enter new ingredients: ");
                            String newIngredients = scanner.nextLine();

                            menu.updateMenuItem(editItemName, newPrice, newIngredients);
                            System.out.println("MenuItem edited successfully.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid price.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter item name to delete: ");
                        String deleteItemName = scanner.nextLine();
                        menu.removeMenuItem(deleteItemName);
                        System.out.println("MenuItem deleted successfully.");
                        break;

                    case 4:
                        menu.printMenu();
                        break;

                    case 5:
                        System.out.print("Enter item name/id to search: ");
                        String searchItem = scanner.nextLine();
                        MenuItem foundItem = menu.search(searchItem);
                        if (foundItem != null) {
                            System.out.println(foundItem);
                        } else {
                            System.out.println("Item not found in the menu.");
                        }
                        break;

                    case 6:
                        List<Reservation> allReservations = reservationManager.getAllReservations();
                        System.out.println("All Reservations:");
                        for (Reservation reservation : allReservations) {
                            System.out.println(reservation);
                        }
                        break;

                    case 7:
                        System.out.print("Enter customer name to search: ");
                        String customerName1 = scanner.nextLine();
                        List<Reservation> reservationsByCustomerName = reservationManager.searchReservationsByCustomerName(customerName1);
                        System.out.println("Reservations for customer " + customerName1 + ":");
                        for (Reservation reservation : reservationsByCustomerName) {
                            System.out.println(reservation);
                        }
                        break;

                    case 8:
                        System.out.println("Going back to Main Menu.");
                        return;

                    case 0:
                        System.out.println("Exiting Staff Interface.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    private static void runCustomerInterface(Scanner scanner) {
        int customerChoice;

        while (true) {
            System.out.println("===== Customer Interface =====");
            System.out.println("1. View Menu");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservation");
            System.out.println("4. Update Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Go back to Main Menu");
            System.out.println("0. Exit");

            try {
                System.out.print("Enter your choice: ");
                customerChoice = scanner.nextInt();
                scanner.nextLine();

                switch (customerChoice) {
                    case 1:
                        menu.printMenu();
                        break;

                    case 2:
                        System.out.print("Enter customer name: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        System.out.print("Enter time: ");
                        String time = scanner.nextLine();
                        System.out.print("Enter number of guests: ");

                        try {
                            int numberOfGuests = scanner.nextInt();
                            scanner.nextLine();

                            Reservation newReservation = reservationManager.createReservation(customerName, date, time, numberOfGuests, menu);

                            if (newReservation != null) {
                                addMealChoicesToReservation(newReservation, scanner);

                                System.out.println("Reservation created successfully.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer for the number of guests.");
                            scanner.nextLine();
                        }
                        break;


                    case 3:
                        System.out.print("Enter reservation ID to view: ");

                        try {
                            int viewReservationId = scanner.nextInt();
                            Reservation viewReservation = reservationManager.getReservationById(viewReservationId);
                            if (viewReservation != null) {
                                System.out.println(viewReservation);
                            } else {
                                System.out.println("Reservation not found.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer for the reservation ID.");
                            scanner.nextLine();
                        }
                        break;

                    case 4:
                        System.out.print("Enter reservation ID to update: ");

                        try {
                            int updateReservationId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new date (YYYY-MM-DD): ");
                            String newDate = scanner.nextLine();
                            System.out.print("Enter new time: ");
                            String newTime = scanner.nextLine();
                            System.out.print("Enter new number of guests: ");

                            try {
                                int newNumberOfGuests = scanner.nextInt();
                                scanner.nextLine();

                                reservationManager.updateReservation(updateReservationId, newDate, newTime, newNumberOfGuests);
                                System.out.println("Reservation updated successfully.");
                            } catch (InputMismatchException ex) {
                                System.out.println("Invalid input. Please enter a valid integer for the number of guests.");
                                scanner.nextLine();
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer for the reservation ID.");
                            scanner.nextLine();
                        }
                        break;

                    case 5:
                        System.out.print("Enter reservation ID to cancel: ");

                        try {
                            int cancelReservationId = scanner.nextInt();
                            reservationManager.deleteReservation(cancelReservationId);
                            System.out.println("Reservation canceled successfully.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer for the reservation ID.");
                            scanner.nextLine();
                        }
                        break;

                    case 6:
                        System.out.println("Going back to Main Menu.");
                        return;

                    case 0:
                        System.out.println("Exiting Customer Interface.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    private static void addMealChoicesToReservation(Reservation reservation, Scanner scanner) {
        System.out.println("Select meals for the reservation (enter 'done' when finished):");
        String mealChoice;
        do {
            System.out.print("Enter meal name: ");
            mealChoice = scanner.nextLine();
            if (!mealChoice.equalsIgnoreCase("done")) {
                reservation.addMeal(mealChoice);
            }
        } while (!mealChoice.equalsIgnoreCase("done"));
    }
}
