package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Airport implements Serializable {
    private final int code;

    private String name;

    private int road;

    private int Distanse;

    public Airport(int code, String name, int road, int Distanse){
        this.code = code;
        this.name = name;
        this.road = road;
        this.Distanse = Distanse;
    }

    public String getAirportName() {
        return this.name;
    }

    public Integer getAirportCode(){
        return this.code;
    }

    public Integer getDistance(){
        return this.Distanse;
    }

    public Integer getRoadCode(){
        return this.road;
    }

    public void setRoadName(String name) {
        this.name = name;
    }

    public void setAirportRoad(int road) {
        this.road = road;
    }

    public void setAirportDistace(int price) {
        this.Distanse = price;
    }

    public static String printProduct(ArrayList<Airport> airports){
        StringBuilder result = new StringBuilder();
        for (Airport airport : airports){
            result.append(">>").append(airport.getAirportCode()).append("\n")
                    .append("Name: ").append(airport.getAirportName()).append("\n")
                    .append("Road: ").append(airport.getRoadCode()).append("\n")
                    .append("Distance: ").append(airport.getDistance()).append("\n");
        }
        return result.toString();
    }
}
