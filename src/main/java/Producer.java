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

		/*quantite_eau.set(getQuantitéEau-(quantité eau utilisé pour un café))*/
		/*quantite_cafe.set(getQuantitéCafe-(quantité cafe utilisé pour un café))*/
		/*si quantite_eau<seuil{
			String messageEau = "Le seuil d'eau de la machine à café a été atteind, pensez à re remplir"
			channel.basicPublish(EXCHANGE_NAME, "", null,messagesEau.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + messageEau + "'");
		}
		/*si quantite_eau<seuil{
			String messageCafe = "Faites attention, il ne vous reste du café que pour très peu de tasse encore, pensez à le remplir"
			channel.basicPublish(EXCHANGE_NAME, "", null,messagesCafe.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + messageCafe + "'");
   		 }*/
  }
}
}