package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Road implements Serializable {

    public int code;

    public String name;

    public Road(int code, String name){
        this.code = code;
        this.name = name;
    }

    public String getRoadName() {
            return this.name;
    }

    public Integer getRoadCode(){
        return this.code;
    }

    public void setRoadName(String name) {
        this.name = name;
    }

    public static String printRoad(ArrayList<Road> roads){
        StringBuilder result = new StringBuilder();
        for (Road road : roads){
            result.append(">>").append(road.getRoadCode())
                    .append("-").append(road.getRoadName()).append("\n");
        }
        return result.toString();
    }

}
