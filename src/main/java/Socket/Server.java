package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    private static ServerSocket server = null;
    private static Socket sock = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;

    public static void main(String[] args) {
        try {
            Server srv = new Server();
            srv.start(12345);
        } catch (IOException | SQLException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            System.out.println("Error");
        }
    }

    public void start(int port) throws IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        server = new ServerSocket(port);
        while (true) {
            sock = server.accept();
            in = new BufferedReader(new
                    InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);
            while (processQuery()) ;
        }
    }

    public static boolean processQuery() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ServerService service = new ServerService();
        try {
            String query = in.readLine();
            String[] parameters = query.split("#");
            String command = parameters[0];
            String response = "";
            switch (command) {
                case "1" -> response = service.addRoad(Integer.parseInt(parameters[1]), parameters[2]);
                case "2" -> response = service.deleteRoad(Integer.parseInt(parameters[1]));
                case "3" -> response = service.updateRoad(Integer.parseInt(parameters[1]), parameters[2]);
                case "4" -> response = service.addAirport(Integer.parseInt(parameters[1]), parameters[2], Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));
                case "5" -> response = service.deleteAirport(Integer.parseInt(parameters[1]));
                case "6" -> response = service.updateAirport(Integer.parseInt(parameters[1]), parameters[2], Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));
                case "7" -> response = service.showAirportinRoad(Integer.parseInt(parameters[1]));
                case "8" -> response = service.getAirportByName(parameters[1]);
                case "9" -> response = service.showAirportInRoad(Integer.parseInt(parameters[1]));
                case "10" -> response = service.showRoads();
                case "11" -> {
                    sock.close();
                    in.close();
                    out.close();
                    return false;
                }
            }
            out.println(response);
            out.flush();
            return true;
        } catch (Exception e) {
            System.out.println("SERVER: Error: " + e.getMessage());
            return false;
        }
    }
}