package JMS.RabbitMQ;

import Database.DatabaseService;
import com.rabbitmq.client.*;

import java.sql.SQLException;

import static Data.Airport.printProduct;
import static Data.Road.printRoad;

public class Recv {

    private final static String QUEUE_NAME = "main_queue";
    private static DatabaseService service;

    public static void main(String[] argv) throws Exception {
        service = new DatabaseService("jdbc:mysql://localhost:3306/airport", "root", "8888");
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.queuePurge(QUEUE_NAME);

            channel.basicQos(1);


            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();
                System.out.println("The server received the request\n");
                String response = "";
                try {
                    String command = new String(delivery.getBody(), "UTF-8");
                    response =  processQuery(command);
                } catch (RuntimeException e) {
                    System.out.println(" [.] " + e);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println("The server sent a response to the request\n");
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(QUEUE_NAME, false, deliverCallback, (consumerTag -> {}));
        }

    public static String processQuery(String query) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String[] parameters = query.split("#");
        String command = parameters[0];
        switch (command) {
            case "1" -> {
                service.addRoad(Integer.parseInt(parameters[1]), parameters[2]);
                return "Road successfully added";
            }
            case "2" -> {
                service.addAirport(Integer.parseInt(parameters[1]), parameters[2], Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));
                return "Airport successfully added";
            }
            case "3" -> {
                service.deleteAirport(Integer.parseInt(parameters[1]));
                return "Airport successfully deleted";
            }
            case "4" -> {
                return printProduct(service.showAirportRoad(Integer.parseInt(parameters[1])));
            }
            case "5" -> {
                return printRoad(service.showSections());
            }
        }
        return "";
    }
}