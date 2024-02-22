import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Random;

public class Producer {

	private static final String EXCHANGE_NAME = "logs";
	private static final String ROUTING_KEY = "#my_route";
	private static final String BROKER_HOST = System.getenv("broker_host");

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
	 
	 Random random = new Random();
     double temperature = Maths.random()*100;
     factory.setHost("BROKER_HOST");

    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
         String message = "La température est de: " + temperature + " degrés";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");
    }
  }
}
