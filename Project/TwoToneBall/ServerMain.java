package TwoToneBall;

import java.net.DatagramSocket;

public class ServerMain {

	public static void main(String[] args) {
		try {
			Server server = new Server(new DatagramSocket(10086));
			Model model = new Model();
			new ServerView(model, server);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
