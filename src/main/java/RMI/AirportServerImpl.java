package RMI;

import Data.Airport;
import Data.Road;
import Database.DatabaseService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class AirportServerImpl extends UnicastRemoteObject implements AirportServer {
    private static DatabaseService service;

    public AirportServerImpl() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, RemoteException {
         service = new DatabaseService("jdbc:mysql://localhost:3306/shop", "root", "06102003");
    }

    @Override
    public void addRoad(int id, String name) throws RemoteException {
        service.addRoad(id, name);
    }

    @Override
    public void deleteRoad(int id) throws RemoteException {
         service.deleteRoad(id);
    }

    @Override
    public void updateRoad(int id, String name) throws RemoteException {
          service.updateRoad(id, name);
    }

    @Override
    public void addAirport(int id, String name, int road, int distance) throws RemoteException {
        service.addAirport(id, name, road, distance);
    }

    @Override
    public void deleteAirport(int id) throws RemoteException {
         service.deleteAirport(id);
    }

    @Override
    public void updateAirport(int id, String name, int section, int distance) throws RemoteException {
            service.updateAirport(id, name, section, distance);
    }

    @Override
    public int showRoadsinAirport(int id) throws RemoteException {
        return service.showRoadsinAirport(id);
    }

    @Override
    public Airport getAirportByName(String name) throws RemoteException {
        return service.getAirportByName(name);
    }

    @Override
    public ArrayList<Airport> showAirportRoad(int id) throws RemoteException {
        return service.showAirportRoad(id);
    }

    @Override
    public ArrayList<Road> showRoad() throws RemoteException {
        return service.showSections();
    }

}