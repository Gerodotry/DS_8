package Socket;

import Data.Airport;
import Data.Road;
import Database.DatabaseService;

import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;

public class ServerService {

   private DatabaseService service;
   private StringWriter result;
    public ServerService()
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
         service = new DatabaseService("jdbc:mysql://localhost:3306/airport", "root", "06102003");
         result = new StringWriter();
    }

    public String showRoads() {
        ArrayList<Road> roads = service.showSections();
        result.append("ALL ROADS:#");
        for (Road road : roads){
            result.append(">>" + road.getRoadCode() + "-" + road.getRoadName()).append("#");
        }
        return result.toString();
    }

    public String showAirportInRoad(int code) {
        ArrayList<Airport> airports = service.showAirportRoad(code);
        result.append("Airport In Road " + code + ":#");
        for (Airport airport : airports){
            result.append(">>" + airport.getAirportCode() + "-" + airport.getAirportName() + " $" + airport.getDistance()).append("#");
        }
        return result.toString();
    }

    public String addRoad(int id, String name) {
        try {
            service.addRoad(id, name);
            result.append("Road ").append(name).append(" successfully added#");
        } catch (Exception e) {
            result.append("Failed to add Road ").append(name).append("#");
        }
        return result.toString();
    }


    public String updateRoad(int id, String name) {
        try {
            service.updateRoad(id, name);
            result.append("Road ").append(name).append(" successfully updated#");
        } catch (Exception e) {
            result.append("Failed to update Road ").append(name).append("#");
        }
        return result.toString();
    }

    public String updateAirport(int id, String name, int Distance, int price) {
        try {
            service.updateAirport(id, name, Distance, price);
            result.append("Airport ").append(name).append(" successfully updated#");
        } catch (Exception e) {
            result.append("Failed to update Airport ").append(name).append("#");
        }
        return result.toString();
    }

    public String addAirport(int id, String name, int Distance, int price ) {
        try {
            service.addAirport(id, name, Distance, price);
            result.append("Airport ").append(name).append(" successfully added#");
        } catch (Exception e) {
            result.append("Failed to add Airport ").append(name).append("#");
        }
        return result.toString();
    }

    public String deleteRoad(int id) {
        try {
            service.deleteRoad(id);
            result.append("Road ").append((char) id).append(" successfully deleted#");
        } catch (Exception e) {
            result.append("Failed to delete Road ").append((char) id).append("#");
        }
        return result.toString();
    }

    public String deleteAirport(int id) {
        try {
            service.deleteAirport(id);
            result.append("Airport ").append((char) id).append(" successfully deleted#");
        } catch (Exception e) {
            result.append("Failed to delete Airport ").append((char) id).append("#");
        }
        return result.toString();
    }

    public String showAirportinRoad(int code) {
        char airportCount = (char) service.showRoadsinAirport(code);
        result.append("number of Airport in Road ").append((char) code).append(": ").append(airportCount).append("#");
        return result.toString();
    }

    public String getAirportByName(String name) {
        Airport airport = service.getAirportByName(name);
        result.append("Airport with name ").append(name).append(" has :#");
        result.append("Id: ").append(airport.getAirportCode().toString())
                .append("#Road: ").append(airport.getRoadCode().toString())
                .append("#Distance: ").append(airport.getDistance().toString()).append("#");
        return result.toString();
    }


}
