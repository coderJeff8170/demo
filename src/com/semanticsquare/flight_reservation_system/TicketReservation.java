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
        //if confirmed list is below capacity,
        if(confirmedList.size() < CONFIRMEDLIST_LIMIT) {
            //add passenger
            confirmedList.add(passenger);
            //update boolean to true
            isPassengerAdded = true;
        } else if (waitingList.size() < WAITINGLIST_LIMIT) {
            //add passenger
            waitingList.add(passenger);
            // update boolean to true
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
        //if passenger with conf number is in confirmed list, remove them, then add(polled passenger from front of waiting list)
        //loop thru confirmed list
//        for(Passenger passenger : confirmedList) {
//            if(passenger.getConfirmationNumber() == confirmationNumber){
                //remove passenger
//                confirmedList.remove(passenger);//concurrent modification error! use the iterator method
                Iterator<Passenger> iterator = confirmedList.iterator();
                isPassengerRemoved = removePassenger(iterator, confirmationNumber);
                //add passenger at top of wait list
                //if passenger was removed and waiting list has somebody, add that somebody
                if(isPassengerRemoved == true && waitingList.size()>0) {
                    confirmedList.add(waitingList.poll());//might need to use the iterator?
                }else if(isPassengerRemoved == false) {
                    //reuse iterator
                    iterator = waitingList.iterator();
                    isPassengerRemoved = removePassenger(iterator, confirmationNumber);
//                    for(Passenger passenger : waitingList) {
//                        if(passenger.getConfirmationNumber() == confirmationNumber){
//                            waitingList.remove(passenger);
//                            isPassengerRemoved = true;
//                        }
//                    }
                }

        //else if passenger with conf number is in waiting list, remove them
        return isPassengerRemoved;
    }

    /**
     * Removes passenger with the given confirmation number.
     * Returns true only if passenger was present and removed. Otherwise, return false.
     */
    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        boolean isPassengerRemoved = false;
        //iterate through list, and if passenger with confnumber present, removed them
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
        System.out.println(reservation.bookFlight("Jeff", "Andrews", 52,
                "male", "business", "C9"));
        System.out.println(        reservation.bookFlight("Jenn", "Andrews", 42,
                "female", "economy", "A8"));
        System.out.println(        reservation.bookFlight("Pookie", "Andrews", 15,
                "female", "economy", "A7"));
//        System.out.println(reservation.confirmedList.get(0).getFirstName());
        System.out.println(reservation.cancel("C9"));
        for (Passenger passenger : reservation.confirmedList) {
            System.out.print(passenger.getFirstName() + " ");
        }
    }

}
