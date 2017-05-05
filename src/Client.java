import java.io.IOException;
import java.util.Scanner;

import com.lloseng.ocsf.client.ObservableClient;

public class Client extends ObservableClient{
	
	public Client(String host, int port) {
		super(host, port);
	}
	
	public void handleMessageFromServer(Object message) {
		System.out.println(message);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		Client clientServer = new Client("10.2.11.127", 5001);
		clientServer.run();
		while(clientServer.isConnected()) {
			String input = scanner.nextLine();
			if(input.toString().equalsIgnoreCase("quit")) {
				clientServer.closeConnection();
			} else{
				clientServer.sendToServer(input);
			}
		}
	}
	
	public void run() {
		try {
			this.openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
