package com.semanticsquare.flight_reservation_system;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class TicketReservation {

    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();//double ended queue - we want to be able to get the person next in line (FIFO) by polling

    // This getter is used only by the junit test case.
    public List<Passenger> getConfirmedList() {
        return confirmedList;
    }

    /**
     * Returns true if passenger could be added into either confirmedList or
     * waitingList. Passenger will be added to waitingList only if confirmedList
     * is full.
     *
     * Return false if both passengerList & waitingList are full
     */
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber) {

        boolean isPassengerAdded = false;
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);

        if(confirmedList.size() < CONFIRMEDLIST_LIMIT) {
            confirmedList.add(passenger);
            isPassengerAdded = true;
        } else if (waitingList.size() < WAITINGLIST_LIMIT) {
            System.out.println("adding passenger to wait list!");
            waitingList.add(passenger);
            isPassengerAdded = true;
        }

        return isPassengerAdded;
    }

    /**
     * Removes passenger with the given confirmationNumber. Passenger could be
     * in either confirmedList or waitingList. The implementation to remove the
     * passenger should go in removePassenger() method and removePassenger method
     * will be tested separately by the uploaded test scripts.

     * If passenger is in confirmedList, then after removing that passenger, the
     * passenger at the front of waitingList (if not empty) must be moved into
     * passengerList. Use poll() method (not remove()) to extract the passenger
     * from waitingList.
     */
    public boolean cancel(String confirmationNumber) {

        boolean isPassengerRemoved = false;
        Iterator<Passenger> iterator = confirmedList.iterator();
        isPassengerRemoved = removePassenger(iterator, confirmationNumber);

        if(isPassengerRemoved == true && waitingList.size() > 0) {
            confirmedList.add(waitingList.poll());
        } else if (isPassengerRemoved == false) {
            iterator = waitingList.iterator();
            isPassengerRemoved = removePassenger(iterator, confirmationNumber);
        }

        return isPassengerRemoved;
    }

    /**
     * Removes passenger with the given confirmation number.
     * Returns true only if passenger was present and removed. Otherwise, return false.
     */
    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {

        boolean isPassengerRemoved = false;

        while(iterator.hasNext()) {

            Passenger passenger = iterator.next();

            if(passenger.getConfirmationNumber() == confirmationNumber) {
                iterator.remove();
                isPassengerRemoved = true;
            }
        }

        return isPassengerRemoved;
    }


    public static void main (String[]  args) {

        TicketReservation reservation = new TicketReservation();
        reservation.bookFlight("Jeff", "Andrews", 52,
                "male", "business", "C9");
        reservation.bookFlight("Jenn", "Andrews", 42,
                "female", "economy", "Z8");
        reservation.bookFlight("Pookie", "Andrews", 15,
                "female", "economy", "A7");
        reservation.bookFlight("Jeff", "Andrews", 52,
                "male", "business", "C5");
        reservation.bookFlight("Jenn", "Andrews", 42,
                "female", "economy", "A3");
        reservation.bookFlight("Pookie", "Andrews", 15,
                "female", "economy", "Q7");
        reservation.bookFlight("Jeff", "Andrews", 52,
                "male", "business", "C6");
        reservation.bookFlight("Jenn", "Andrews", 42,
                "female", "economy", "B8");
        reservation.bookFlight("Pookie", "Andrews", 15,
                "female", "economy", "J7");
        reservation.bookFlight("Jeff", "Andrews", 52,
                "male", "business", "C0");
        reservation.bookFlight("Jenn", "Andrews", 42,
                "female", "economy", "J1");
        reservation.bookFlight("Pookie", "Andrews", 15,
                "female", "economy", "M7");
//        System.out.println(reservation.confirmedList.get(0).getFirstName());
//        System.out.println(reservation.cancel("C9"));
//        System.out.println(reservation.cancel("J1"));
        System.out.println("confirmed list:");
        for (Passenger passenger : reservation.confirmedList) {
            System.out.print(passenger.getFirstName() + " ");
        }
        System.out.println("\nwaiting list");
        for (Passenger passenger : reservation.waitingList) {
            System.out.print(passenger.getFirstName() + " ");
        }
    }

}
