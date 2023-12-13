package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private  final Socket sock;
    private static PrintWriter out;
    private static  BufferedReader in;

    public Client(String ip, int port) throws IOException {
        sock = new Socket(ip, port);
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new PrintWriter(sock.getOutputStream(), true);
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 12345);
            showRoads();
            readResponse();

            getAirportByName("jshdv");
            readResponse();

            addAirport("44","asmdh","55", "2");
            readResponse();

            showAirportsinRoad("2");
            readResponse();

            stop();
            client.disconnect();
        } catch (IOException e) {
            System.out.println("CLIENT: Error");
            e.printStackTrace();
        }
    }

    private static String sendRequest(String... requests) {
        StringBuilder request = new StringBuilder();
        for (String arg : requests) {
            request.append(arg).append("#");
        }
       return request.toString();
    }

    private static void readResponse() throws IOException {
        String response = in.readLine();
        String[] fields = response.split("#");
        for (String field : fields) {
            System.out.println(field);
        }
        System.out.println();
    }

    public static void addRoad(String id, String name) throws IOException {
        out.println(sendRequest("1", id, name));
    }

    public static void deleteRoad(String id) throws IOException {
        out.println(sendRequest("2", id));
    }

    public static void updateRoad( String id, String name) throws IOException {
        out.println(sendRequest("3", id, name));
    }

    public static void addAirport(String id, String name, String price, String Distance) throws IOException {
        out.println(sendRequest("4", id, name, Distance, price));
    }

    public static void deleteAirport(String id) throws IOException {
        out.println(sendRequest("5", id));
    }

    public static void updateAirport(String id, String name, String price, String Distance) throws IOException {
        out.println(sendRequest("6", id, name, Distance, price));
    }

    public static void showAirportCountInRoad(String id) throws IOException {
        out.println(sendRequest("7", id));
    }

    public static void getAirportByName(String name) throws IOException {
        out.println(sendRequest("8",name));
    }

    public static void showAirportsinRoad(String id) throws IOException {
        out.println(sendRequest("9", id));
    }

    public static void showRoads() throws IOException {
        out.println(sendRequest("10"));
    }

    public static void stop() throws IOException {
        out.println(sendRequest("11"));
    }


    public void disconnect() throws IOException {
        sock.close();
    }
}