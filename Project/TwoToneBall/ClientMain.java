package TwoToneBall;

public class ClientMain {

	public static void main(String[] args) {
		Client client = new Client();
		ClientView clientView = new ClientView();
		new Controller(client, clientView);
	}

}
