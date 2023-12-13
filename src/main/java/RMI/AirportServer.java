package RMI;

import Data.Airport;
import Data.Road;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AirportServer extends Remote {
    public void addRoad(int id, String name) throws RemoteException;

    public void deleteRoad(int id) throws RemoteException;

    public void updateRoad(int id, String name) throws RemoteException;

    public void addAirport(int id, String name, int road, int distance) throws RemoteException;

    public void deleteAirport(int id) throws RemoteException;

    public void updateAirport(int id, String name, int section, int price) throws RemoteException;

    public int showRoadsinAirport(int id) throws RemoteException;

    public Airport getAirportByName(String name) throws RemoteException;

    public ArrayList<Airport> showAirportRoad(int id) throws RemoteException;

    public ArrayList<Road> showRoad() throws RemoteException;

}

