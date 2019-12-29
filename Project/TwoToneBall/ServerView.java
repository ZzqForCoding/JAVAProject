package TwoToneBall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//投注信息滚动轴
public class ServerView implements Serializable {
	private JFrame jFrame;
	private Model model;
	private Server server;
	private JPanel jp2;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel7;
	private JTextArea jTextArea;
	private ArrayList<Integer> list;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private ArrayList<String> nameList;
	private HashMap<String, ArrayList<ArrayList<Integer>>> hashMap;
	private SimpleDateFormat simpleDateFormat;
	private Date d1;
	private Date d2;
	private JLabel jLabel5;
	private JLabel jLabel1;

	private String now;
	private String showPriceTime;
	private String s1;
	private String s2;

	public ServerView(Model model, Server server) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("b.txt"));
			oos.writeObject(this);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.model = model;
		this.server = server;
		jFrame = new JFrame("服务器");
		jFrame.setLayout(new FlowLayout());
		// 点击叉叉就执行关闭程序
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口大小与可见度
		jFrame.setSize(580, 650);
		jFrame.setLocation(1000, 150);
		jFrame.setResizable(false);
		AddComponent();
		jFrame.setVisible(true);

	}

	public void AddComponent() {
		jp2 = new JPanel(new BorderLayout());
		jp2.setPreferredSize(new Dimension(490, 50));
		d2 = new Date(System.currentTimeMillis() + 30000);
		now = "当前时间:";
		jLabel2 = new JLabel("");
		jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
		showPriceTime = "开奖时间:";
		jLabel3 = new JLabel(showPriceTime + simpleDateFormat.format(d2.getTime()));
		jLabel3.setFont(new Font("宋体", Font.BOLD, 15));

		jp2.add(jLabel2, BorderLayout.NORTH);
		jp2.add(jLabel3, BorderLayout.SOUTH);

		jFrame.add(jp2);

		JPanel jp3 = new JPanel();
		jp3.setPreferredSize(new Dimension(570, 25));
		jp3.setBackground(Color.PINK);
		JLabel jLabe3 = new JLabel("投注情况:                         ");
		jLabe3.setFont(new Font("宋体", Font.BOLD, 15));
		jp3.add(jLabe3);
		jFrame.add(jp3);

		JPanel jp4 = new JPanel();
		jp4.setBackground(Color.cyan);
		jp4.setPreferredSize(new Dimension(570, 25));
		JLabel jLabe4 = new JLabel("姓名      注数      投注金额");
		jLabe4.setFont(new Font("宋体", Font.BOLD, 15));
		jp4.add(jLabe4);

		jFrame.add(jp4);

		JPanel jp5 = new JPanel();
		jTextArea = new JTextArea(11, 25);
		jTextArea.setEditable(false);
		jp5.add(jTextArea);

		jFrame.add(jp5);

		JPanel jp6 = new JPanel();
		jp6.setBackground(Color.MAGENTA);
		jp6.setPreferredSize(new Dimension(570, 100));
		jLabel5 = new JLabel("开奖啦！！！！");
		jLabel5.setFont(new Font("宋体", Font.BOLD, 20));
		jp6.add(jLabel5);
		jLabel5.setVisible(false);

		ImageIcon icon = new ImageIcon("D:\\MyFile\\java\\project\\Project\\TwoToneBall\\双色球.jpg");
		icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		jLabel1 = new JLabel(icon);
		jp6.add(jLabel1);
		jLabel1.setVisible(false);
		jFrame.add(jp6);

		JPanel jp7 = new JPanel(new BorderLayout());
		jp7.setBackground(Color.lightGray);
		jp7.setPreferredSize(new Dimension(450, 40));
		JLabel jLabe6 = new JLabel(
				"        红一          红二           红三          红四          红五          红六            蓝          ");
		jLabel7 = new JLabel("");
		jp7.add(jLabe6, BorderLayout.NORTH);
		jp7.add(jLabel7, BorderLayout.SOUTH);

		jFrame.add(jp7);

		JPanel jP8 = new JPanel();
		jP8.setBackground(Color.blue);
		jP8.setPreferredSize(new Dimension(490, 50));
		s1 = new String("本次中奖情况:  一等奖");
		jLabel8 = new JLabel(s1);
		jLabel8.setFont(new Font("宋体", Font.BOLD, 25));
		jP8.add(jLabel8);

		jFrame.add(jP8);

		JPanel jP9 = new JPanel();
		jP9.setBackground(Color.CYAN);
		jP9.setPreferredSize(new Dimension(490, 50));
		s2 = new String("              二等奖");
		jLabel9 = new JLabel(s2);
		jLabel9.setFont(new Font("宋体", Font.BOLD, 25));
		jP9.add(jLabel9);
		jLabel8.setVisible(false);
		jLabel9.setVisible(false);
		

		jFrame.add(jP9);
		new Thread() {
			public void run() {
				while (true) {
					d1 = new Date();
					jLabel2.setText(now + simpleDateFormat.format(d1));
				}
			}
		}.start();
		new Thread() {
			public void run() {
				while (true) {
					try {
						System.out.println("");
						if (d1.compareTo(d2) >= 0) {
							server.send("-10".getBytes());
							HashSet<Integer> hashSet = new HashSet<Integer>();
							list = new ArrayList<Integer>();
							for (int i = 0; i < 7; i++) {
								int num = 0;
								if ((i + 1) % 7 == 0) {
									do {
										num = (int) (Math.random() * 16 + 1);
									} while (hashSet.contains(num));
								} else {
									do {
										num = (int) (Math.random() * 33 + 1);
									} while (hashSet.contains(num));
								}
								hashSet.add(num);
								list.add(num);
							}
							model.ExcuteInsTwo(list);
							String s = "         ";
							for (int i = 0; i < 7; i++) {
								d2 = new Date(System.currentTimeMillis() + 5000);
								if (i == 6) {
									jLabel3.setText("蓝球" + showPriceTime + simpleDateFormat.format(d2));
								} else {
									jLabel3.setText("红球" + i + showPriceTime + simpleDateFormat.format(d2));
								}
								while (true) {
									Thread.sleep(500);
									if (d1.compareTo(d2) >= 0) {
										s = s + list.get(i) + "               ";
										if (list.get(i) < 10)
											s += " ";
										jLabel7.setText(s);
										break;
									}
								}
							}
							jLabel3.setText("即将开奖....");
							Thread.sleep(3000);
							// 显示中奖人
							ShowPrice();
							jLabel3.setText("已开奖！！！");
							jLabel5.setVisible(true);
							jLabel1.setVisible(true);
							Thread.sleep(5000);
							// 所有客户端将下注清空
							model.initial();
							initial();
							server.send("-9".getBytes());
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		new Thread() {
			public void run() {
				while (true) {
					try {
						model.ExcuteSelect();
						Thread.sleep(1000);
						nameList = model.getNameList();
						hashMap = model.getHashMap();
						String s = "";
						if (nameList.size() != 0) {
							int n = nameList.size();
							for (int i = 0; i < n; i++) {
								String name = nameList.get(i);
								int m = model.getHashMap().get(name).size();
								s += "        " + name + "                            " + m
										+ "                             " + m * 2 + "\n";
							}
						}
						jTextArea.setText(s);
						s = "";
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();
	}

	public void ShowPrice() {
		HashMap<String, Integer> priceList = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < nameList.size(); j++) {
				String name = nameList.get(j);
				ArrayList<ArrayList<Integer>> aList = hashMap.get(name);
				for (int k = 0; k < aList.size(); k++) {
					ArrayList<Integer> bList = aList.get(k);
					for (int l = 0; l < bList.size(); l++) {
						int num = 0;
						if (priceList.get(name) != null)
							num = priceList.get(name);
						if (bList.get(i) == list.get(i) && i != list.size() - 1) {
							num += 2;
						} else if (bList.get(i) == list.get(i) && i == list.size() - 1) {
							num += 1;
						}
						priceList.put(name, num);
					}
				}
			}
		}
		List<Map.Entry<String, Integer>> map = new ArrayList<Map.Entry<String, Integer>>(priceList.entrySet());
		Collections.sort(map, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue().toString().compareTo(o2.getValue().toString());
			}
		});
		if (map.size() > 0 && map.get(0) != null) {
			s1 = s1 + ": " + map.get(0).getKey();
			jLabel8.setText(s1);
			jLabel8.setVisible(true);
		}
		if (map.size() > 1 && map.get(1) != null) {
			s2 = s2 + ": " + map.get(1).getKey();
			jLabel9.setText(s2);
			jLabel9.setVisible(true);
		}
	}

	public JTextArea getjTextArea() {
		return jTextArea;
	}

	public void initial() {
		jTextArea.setText("");
		s1 = new String("本次中奖情况:  一等奖");
		jLabel8.setText(s1);
		jLabel8.setVisible(false);
		s2 = new String("              二等奖");
		jLabel9.setText(s2);
		jLabel9.setVisible(false);
		jLabel5.setVisible(false);
		jLabel1.setVisible(false);
		jLabel7.setText("");
		d2 = new Date(System.currentTimeMillis() + 30000);
		jLabel3.setText(showPriceTime + simpleDateFormat.format(d2.getTime()));
	}

}
