package Database;

import Data.Airport;
import Data.Road;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseService {
    private final Connection connection;
    private final Statement statement;
    private final StringBuilder result;

    public DatabaseService(String url, String ip, String port)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url, ip, port);
        statement = connection.createStatement();
        result = new StringBuilder();
    }

    public ArrayList<Road> showSections() {
        String sql = "SELECT * FROM ROAD";
        try {
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Road> roads = new ArrayList<Road>();
            while (rs.next()) {
                roads.add(new Road(rs.getInt("ID_S"), rs.getString("NAME")));
            }
            rs.close();
            return roads;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Airport> showAirportRoad(int code) {
        String sql = "SELECT ID_P, NAME, DISTANCE FROM AIRPORT WHERE ID_S = " + code;
        try {
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Airport> airports = new ArrayList<Airport>();
            while (rs.next()) {
                airports.add(new Airport(rs.getInt("ID_P"), rs.getString("NAME"), code, rs.getInt("DISTANCE")));
            }
            rs.close();

            return airports;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void addRoad(int id, String name) {
        String sql = "INSERT INTO ROAD (ID_S, NAME) " +
                "VALUES (" + id + ", '" + name + "')";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRoad(int id, String name) {
        String sql = "UPDATE ROAD SET NAME = '" + name + "' WHERE ID_S = " + id;
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAirport(int id, String name, int section, int price) {
        String sql = "UPDATE AIRPORT SET ID_S =  " + section + ", NAME = '" + name + "', PRICE = " + price + " WHERE ID_P = " + id;
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAirport(int id, String name, int section, int price) {
        String sql = "INSERT INTO AIRPORT (ID_P, NAME, ID_S, PRICE) " +
                "VALUES (" + id + ", '" + name + "' , '" + section + "' , '" + price + "')";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRoad(int id) {
        String sql = "DELETE FROM ROAD WHERE ID_S =" + id;
        try {
             statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAirport(int id) {
        String sql = "DELETE FROM AIRPORT WHERE ID_P =" + id;
        try {
             statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int showRoadsinAirport(int code) {
        String sql = "SELECT COUNT(*) AS AIRPORT_COUNT FROM AIRPORT WHERE ID_S = " + code;
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("AIRPORT_COUNT");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public Airport getAirportByName(String name) {
        String sql = "SELECT * FROM AIRPORT WHERE NAME = '" + name +"'";
        try {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            Airport airport = new Airport(rs.getInt("ID_P"), rs.getString("NAME"), rs.getInt("ID_S"), rs.getInt("DISTANCE"));
            rs.close();
            return airport;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
