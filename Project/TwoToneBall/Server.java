package TwoToneBall;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Server extends Thread {
	private List<HashMap<String, String>> list;
	private DatagramSocket ds;
	private byte[] c = new byte[1024];
	private String ip;
	private String port;

	public Server(DatagramSocket ds) {
		this.ds = ds;
		this.start();
		c[0] = 'e';
		c[1] = 'x';
		c[2] = 'i';
		c[3] = 't';
	}

	public void run() {
		list = new ArrayList<HashMap<String, String>>();
		while (true) {
			byte[] b = new byte[1024];
			DatagramPacket dp = new DatagramPacket(b, b.length);
			try {
				ds.receive(dp);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			ip = dp.getAddress().getHostAddress();
			port = dp.getPort() + "";
			// exit
			if (Arrays.equals(b, c)) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).get("ip").equals(ip) && list.get(i).get("port").equals(port)) {
						list.remove(i);
					}
				}
				if (list.isEmpty()) {
					System.exit(0);
				}
			} else {
				// 判断发件人是否存在于list中
				boolean flag = true;
				for (HashMap<String, String> h : list) {
					if (h.get("ip").equals(ip) && h.get("port").equals(port)) {
						flag = false;
					}
				}
				if (flag) {
					HashMap<String, String> hm = new HashMap<String, String>();
					hm.put("ip", ip);
					hm.put("port", port);
					list.add(hm);
				}
				send(b);
			}
		}
	}

	public void send(byte[] b) {
		try {
			for (HashMap<String, String> h : list) {
				String ip2 = h.get("ip");
				String port2 = h.get("port");
				if (!(ip.equals(ip2) && port.equals(port2))) {
					DatagramPacket dp2 = new DatagramPacket(b, b.length, InetAddress.getByName(ip2),
							Integer.parseInt(port2));
					ds.send(dp2);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ip = port = "";
	}

}
