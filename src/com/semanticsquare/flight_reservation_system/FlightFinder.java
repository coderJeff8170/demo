package com.semanticsquare.flight_reservation_system;

import java.time.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class FlightFinder {

    private Map<String, List<Flight>> allFlights = new HashMap<>();

    public FlightFinder(Map<String, List<Flight>> allFlights) {
        this.allFlights = allFlights;
    }

    public List<NavigableSet<Flight>> findFlights(int dayOfMonth, int month, int year,
                                                  int preferredDepartureStartHour, int preferredDepartureEndHour,
                                                  String departureCity, String arrivalCity, String finalArrivalCity,
                                                  String departureCityTimeZone, String arrivalCityTimeZone) {

        List<NavigableSet<Flight>> result = new ArrayList<>();//this is what gets returned eventually, a list of
        //navigable sets, one for departing flights and one for connecting flights

        // Step 1: Construct ZonedDateTime objects to represent User-specified time interval when flights depart
        // Your code

        //preferredStart
        LocalTime prefDepartureStartTime = LocalTime.of(preferredDepartureStartHour, 0, 0);
        LocalDate prefDepartureStartDate = LocalDate.of(year, month, dayOfMonth);
        LocalDateTime prefDepartureStartDateTime = LocalDateTime.of(prefDepartureStartDate, prefDepartureStartTime);
        ZonedDateTime prefDepartureStartZonedDateTime = ZonedDateTime.of(prefDepartureStartDateTime, ZoneId.of(departureCityTimeZone));

        //preferredEnd
        LocalTime prefDepartureEndTime = LocalTime.of(preferredDepartureEndHour, 0, 0);
        LocalDate prefDepartureEndDate = LocalDate.of(year, month, dayOfMonth);
        LocalDateTime prefDepartureEndDateTime = LocalDateTime.of(prefDepartureEndDate, prefDepartureEndTime);
        ZonedDateTime prefDepartureEndZonedDateTime = ZonedDateTime.of(prefDepartureEndDateTime, ZoneId.of(departureCityTimeZone));


        // Step 2: Find departing flights at departureCity
        List<Flight> allDepartingFlights = allFlights.get(departureCity);//returns a list of flights by departure city

        NavigableSet<Flight> departingflights = new TreeSet<>();

        // Your code
        // Tip: Methods like isAfter can be used to find flights in the specified user time interval
        for( Flight flight : allDepartingFlights) {
            // if(flight isAfter afterprefstarttime && isBefore prefDepEndHour) { add flight to departingflights }
            if( flight.getDepartureTime().isAfter(prefDepartureStartDateTime) &&
                flight.getDepartureTime().isBefore(prefDepartureEndDateTime)) {
                departingflights.add(flight);
                System.out.println(flight.getFlightNumber());
            }
        }





        // Step 3: Find connecting flights
        //   Constraint 1: Departing at arrivalCity (e.g., Dubai) and arrive at finalArrivalCity (e.g., Mumbai)
        //   Constraint 2: Should start at least two hours after the arrival time of the first flight in the above navigable set

        List<Flight> allConnectingFlights = allFlights.get(arrivalCity);

        NavigableSet<Flight> connectingflights = new TreeSet<>();

        // Your code
        for(Flight flight : allConnectingFlights) {
            if(flight.getDepartureCity().equals(arrivalCity) && flight.getArrivalCity().equals(finalArrivalCity)
                && flight.getDepartureTime().isAfter(departingflights.first().getArrivalTime().plusHours(2))) {
                connectingflights.add(flight);
                System.out.println(flight.getFlightNumber());
            }
        }

        result.add(departingflights);
        result.add(connectingflights);

        return result;
    }

    public static void main(String[] args) {
        Flight f1 = new Flight(1, "1", "Emirates", "New York", "Dubai",
                LocalDateTime.of(2017, 12, 20, 9, 0),
                LocalDateTime.of(2017, 12, 20, 9, 0));
        Flight f2 = new Flight(2, "2", "Delta", "San Francisco", "Paris",
                LocalDateTime.of(2017, 12, 20, 9, 0),
                LocalDateTime.of(2017, 12, 20, 9, 0));
        Flight f3 = new Flight(3, "3", "Delta", "San Francisco", "Rome",
                LocalDateTime.of(2017, 12, 20, 9, 0),
                LocalDateTime.of(2017, 12, 20, 9, 0));
        Flight f4 = new Flight(4, "4", "Emirates", "San Francisco", "Dubai",
                LocalDateTime.of(2017, 12, 20, 8, 0),
                LocalDateTime.of(2017, 12, 20, 8, 0));


        Flight f5 = new Flight(5, "5", "Emirates", "San Francisco", "Dubai",
                LocalDateTime.of(2017, 12, 20, 9, 30),
                LocalDateTime.of(2017, 12, 20, 9, 30));
        Flight f6 = new Flight(6, "6", "Delta", "San Francisco", "Dubai",
                LocalDateTime.of(2017, 12, 20, 11, 30),
                LocalDateTime.of(2017, 12, 20, 11, 30));
        Flight f7 = new Flight(7, "7", "Delta", "San Francisco", "Dubai",
                LocalDateTime.of(2017, 12, 20, 12, 30),
                LocalDateTime.of(2017, 12, 20, 12, 30));
        Flight f8 = new Flight(8, "8", "Emirates", "San Francisco", "Dubai",
                LocalDateTime.of(2017, 12, 20, 16, 0),
                LocalDateTime.of(2017, 12, 20, 16, 0));


        Flight f9 = new Flight(9, "9", "Emirates", "Paris", "New Delhi",
                LocalDateTime.of(2017, 12, 20, 9, 0),
                LocalDateTime.of(2017, 12, 20, 9, 0));
        Flight f10 = new Flight(10, "10", "Delta", "Dubai", "Singapore",
                LocalDateTime.of(2017, 12, 20, 9, 0),
                LocalDateTime.of(2017, 12, 20, 9, 0));
        Flight f11 = new Flight(11, "11", "Delta", "Dubai", "Mumbai",
                LocalDateTime.of(2017, 12, 20, 9, 30),
                LocalDateTime.of(2017, 12, 20, 9, 30));
        Flight f12 = new Flight(12, "12", "Emirates", "Dubai", "Mumbai",
                LocalDateTime.of(2017, 12, 20, 10, 30),
                LocalDateTime.of(2017, 12, 20, 10, 30));
        Flight f13 = new Flight(13, "13", "Emirates", "Dubai", "Mumbai",
                LocalDateTime.of(2017, 12, 20, 12, 0),
                LocalDateTime.of(2017, 12, 20, 12, 0));
        Flight f14 = new Flight(14, "14", "Delta", "Dubai", "Mumbai",
                LocalDateTime.of(2017, 12, 20, 14, 0),
                LocalDateTime.of(2017, 12, 20, 14, 0));
        Flight f15 = new Flight(15, "15", "Delta", "Rome", "Mumbai",
                LocalDateTime.of(2017, 12, 20, 14, 0),
                LocalDateTime.of(2017, 12, 20, 14, 0));

        Map<String, List<Flight>> allFlights = new HashMap<>();

        allFlights.put("New York", Arrays.asList(f1));
        allFlights.put("San Francisco", Arrays.asList(f2, f3, f4, f5, f6, f7, f8));
        allFlights.put("Paris", Arrays.asList(f9));
        allFlights.put("Dubai", Arrays.asList(f10, f11, f12, f13, f14));
        allFlights.put("Rome", Arrays.asList(f15));

        List<NavigableSet<Flight>> result = new FlightFinder(allFlights).findFlights(20, 12, 2017, 9, 13, "San Francisco", "Dubai", "Mumbai", "America/Los_Angeles", "Asia/Dubai");
    }

}
