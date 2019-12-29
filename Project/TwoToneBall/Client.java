package TwoToneBall;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author ZzQ
 *
 */
public class Client {
	private DatagramSocket ds;
	private String sendMsg;
	private String receiveMsg;
	private Controller controller;

	public Client() {
		try {
			int port = (int) (Math.random() * (60000 - 10000 + 1) + 10000);
			this.Start(new DatagramSocket(port));
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		}
	}

	public void Start(DatagramSocket ds) {
		this.ds = ds;
		try {
			byte[] b2 = "来了个新伙伴,打个招呼吧".getBytes();
			DatagramPacket dp2 = new DatagramPacket(b2, b2.length, InetAddress.getByName("localhost"), 10086);
			ds.send(dp2);
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}

		send();
		receive();
	}

	public void send() {
		new Thread() {
			public void run() {
				try {
					while (true) {
						if (sendMsg != null) {
							byte[] b = sendMsg.getBytes();
							sendMsg = null;
							DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getByName("localhost"),
									10086);
							ds.send(dp);
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}.start();
	}

	public void receive() {
		new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						byte[] b = new byte[1024];
						DatagramPacket dp = new DatagramPacket(b, b.length);
						ds.receive(dp);
						receiveMsg = new String(dp.getData(), 0, dp.getLength());
						if(receiveMsg.equals("-10")) controller.getClientView().setCheck(true);
						else if(receiveMsg.equals("-9")) controller.getClientView().initial();
						else controller.getClientView().getViewArea().append(receiveMsg + "\n");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}.start();
	}

	public void setSendMsg(String Msg) {
		this.sendMsg = Msg;
		send();
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
}
