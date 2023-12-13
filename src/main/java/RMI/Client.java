package RMI;

import Data.Airport;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;

import static Data.Airport.printProduct;
import static Data.Road.printRoad;

public class Client {
    private static AirportServer airport;
    public static void main(String[] args) throws IOException, NotBoundException {
            String url = "//localhost:123/Airport";
            airport = (AirportServer) Naming.lookup(url);
            System.out.println("Connected to the Shop service.");
            System.out.println(showRoad());
            addRoad("11","Missuriu");
            addAirport("35","Mehico","11","500");
            System.out.println(showAirportRoad("11"));
            System.out.println(getAirportByName("Ball"));
    }

    public static void addRoad(String id, String name) throws IOException {
       airport.addRoad(Integer.parseInt(id), name);
    }

    public static void deleteRoad(String id) throws IOException {
        airport.deleteRoad(Integer.parseInt(id));
    }

    public static void updateRoad(String id, String name) throws IOException {
        airport.updateRoad(Integer.parseInt(id), name);
    }

    public static void addAirport(String id, String name, String airportroad, String price) throws IOException {
        airport.addAirport(Integer.parseInt(id), name, Integer.parseInt(airportroad), Integer.parseInt(price));
    }

    public static void deleteAirport(String id) throws IOException {
        airport.deleteAirport(Integer.parseInt(id));
    }

    public static void updateAirport(String id, String name, String price, String airportroad) throws IOException {
        airport.updateAirport(Integer.parseInt(id), name, Integer.parseInt(airportroad), Integer.parseInt(price));
    }

    public static String showRoadsinAirport(String id) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append(airport.showRoadsinAirport(Integer.parseInt(id)) + " Roads in Airport\n");
        return result.toString();
    }

    public static String getAirportByName(String name) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Airports: \n");
        ArrayList<Airport> airports = new ArrayList<Airport>();
        airports.add(airport.getAirportByName(name));
        result.append(printProduct(airports));
        return result.toString();
    }

    public static String showAirportRoad(String id) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("ALL Airport Road " + id + ":\n");
        result.append(printProduct(airport.showAirportRoad(Integer.parseInt(id))));
        return result.toString();
    }

    public static String showRoad() throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("ALL Road:\n");
        result.append(printRoad(airport.showRoad()));
        return result.toString();
    }


}
