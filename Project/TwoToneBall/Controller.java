package TwoToneBall;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Controller {
	private Client client;
	private ClientView clientView;
	private Model model;

	public Controller(Client client, ClientView clientView) {
		try {
			ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("a.txt"));
			this.model = (Model) ois1.readObject();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.client = client;
		this.clientView = clientView;
		client.setController(this);
		clientView.setController(this);
	}
	
	public Client getClient() {
		return client;
	}

	public ClientView getClientView() {
		return clientView;
	}

	public Model getModel() {
		return model;
	}

}
