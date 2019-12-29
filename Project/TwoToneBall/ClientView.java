package TwoToneBall;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

//按两次确定还是可以保存两次,聊天框滚动轴
public class ClientView {
	private ArrayList<JTextField> list;
	private Controller controller;
	private JFrame jFrame;
	private JTextField sendTextField;
	private JButton sendButton;
	private JButton determineButton;
	private JTextArea viewArea;
	private JTextField checkTextField;
	private JTextField nameTextField;
	private ButtonGroup buttonGroup;
	private JRadioButton selectOne;
	private JRadioButton selectTwo;
	private JPanel jPanel;
	private JLabel jLabel;
	private JButton buttonSave;
	private JLabel jl3;
	private SimpleDateFormat sdf;
	private Date d;

	private boolean isCheck;
	private boolean isUpdate = true;
	private boolean isChangeSelectOne = true;
	private boolean isChangeSelectTwo;
	private int preCheckNum;
	boolean isInput;
	private int checknum = -1;
	private String name = "无姓名";
	private int cnt = 1;
	private String sendMsg;

	public ClientView() {
		jFrame = new JFrame("双色球投注系统");
		jFrame.setLayout(new FlowLayout());
		// 点击叉叉就执行关闭程序
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口大小与可见度
		jFrame.setSize(580, 650);
		jFrame.setLocation(200, 150);
		jFrame.setResizable(false);
		AddComponent();
		jFrame.setVisible(true);
		// 关闭窗口此客户端发送exit
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.getClient().setSendMsg("exit");
			}
		});
		// 按下发送键
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMsg = sendTextField.getText();
				if (!sendMsg.equals("")) {
					controller.getClient().setSendMsg(name + ": " + sendMsg);
					sendTextField.setText("");
				}
			}
		});
		// 按enter发送
		sendTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMsg = sendTextField.getText();
					if (!sendMsg.equals("")) {
						controller.getClient().setSendMsg(name + ": " + sendMsg);
						sendTextField.setText("");
					}
				}
			}
		});
		determineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				int temp = judgeCheckNum();
				if (temp == 1) {
					JOptionPane.showMessageDialog(jFrame, "                请输入注数", "提示", JOptionPane.ERROR_MESSAGE);
					flag = false;
				} else if (temp == -1) {
					JOptionPane.showMessageDialog(jFrame, "             注数输入错误", "提示", JOptionPane.ERROR_MESSAGE);
					flag = false;
				} else if (temp == 2) {
					JOptionPane.showMessageDialog(jFrame, "             注数超出范围", "提示", JOptionPane.ERROR_MESSAGE);
					flag = false;
				}
				temp = checkName();
				if (temp == 2 && flag) {
					JOptionPane.showMessageDialog(jFrame, "             姓名超出范围", "提示", JOptionPane.ERROR_MESSAGE);
					flag = false;
				} else if (temp == 1 && flag) {
					JOptionPane.showMessageDialog(jFrame, "              请输入姓名", "提示", JOptionPane.ERROR_MESSAGE);
					flag = false;
				}
				if(flag) SelectInput();
				if (temp == 0 && flag) {
					isUpdate = true;
				}
			}
		});
		selectOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isChangeSelectOne = true;
			}
		});
		selectTwo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isChangeSelectTwo = true;
			}
		});
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isCheck) {
					JOptionPane.showMessageDialog(jFrame, "              已出奖,不能投注", "提示", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean flag = true;
					if (isUpdate) {
						int temp = judgeCheckNum();
						if (temp == 1) {
							JOptionPane.showMessageDialog(jFrame, "                请输入注数", "提示",
									JOptionPane.ERROR_MESSAGE);
							flag = false;
						} else if (temp == -1) {
							JOptionPane.showMessageDialog(jFrame, "             注数输入错误", "提示",
									JOptionPane.ERROR_MESSAGE);
							flag = false;
						} else if (temp == 2) {
							JOptionPane.showMessageDialog(jFrame, "             注数超出范围", "提示",
									JOptionPane.ERROR_MESSAGE);
							flag = false;
						}

						temp = checkName();
						if (temp == 2 && flag) {
							JOptionPane.showMessageDialog(jFrame, "             姓名超出范围", "提示",
									JOptionPane.ERROR_MESSAGE);
							flag = false;
						} else if (temp == 1 && flag) {
							JOptionPane.showMessageDialog(jFrame, "              请输入姓名", "提示",
									JOptionPane.ERROR_MESSAGE);
							flag = false;
						}
						if(flag) SelectInput();
						temp = judgePerNum();
						if (temp == 1 && flag) {
							JOptionPane.showMessageDialog(jFrame, "            下注请输入完整", "提示",
									JOptionPane.ERROR_MESSAGE);
							flag = false;
						} else if (temp == -1 && flag) {
							JOptionPane.showMessageDialog(jFrame, "             下注错误", "提示", JOptionPane.ERROR_MESSAGE);
							flag = false;
						} else if (temp == 2 && flag) {
							JOptionPane.showMessageDialog(jFrame, "              蓝球1-16\n       红球范围:1-33", "提示",
									JOptionPane.ERROR_MESSAGE);
							flag = false;
						} else if (temp == 3 && flag) {
							JOptionPane.showMessageDialog(jFrame, "              球值重复", "提示",
									JOptionPane.ERROR_MESSAGE);
							flag = false;
						}
						if (flag && temp == 0) {
							isUpdate = false;
							// 直接获取到服务器的数据库不太好
							controller.getModel().save(nameTextField.getText().trim(), jl3.getText().substring(8),
									list);
						}
					} else {
						JOptionPane.showMessageDialog(jFrame, "              请勿重复提交", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public void AddComponent() {
		JPanel jp1 = new JPanel(new FlowLayout());
		jp1.setBackground(Color.red);
		jp1.setPreferredSize(new Dimension(550, 60));

		JLabel jl1 = new JLabel("注数");
		checkTextField = new JTextField(15);

		JLabel jl2 = new JLabel("                  姓名");
		nameTextField = new JTextField(15);

		d = new Date();
		sdf = new SimpleDateFormat("yyyyMMdd");
		jl3 = new JLabel("投注期数:   " + sdf.format(d) + cnt);

		JLabel jl4 = new JLabel("       方式: 机选");
		JLabel jl5 = new JLabel("人工 ");
		JLabel jl10 = new JLabel("         ");
		buttonGroup = new ButtonGroup();
		selectOne = new JRadioButton();
		selectTwo = new JRadioButton();
		buttonGroup.add(selectOne);

		buttonGroup.add(selectTwo);
		buttonGroup.setSelected(selectOne.getModel(), true);
		determineButton = new JButton("确认");

		jp1.add(jl1);
		jp1.add(checkTextField);
		jp1.add(jl2);
		jp1.add(nameTextField);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(selectOne);
		jp1.add(jl5);
		jp1.add(selectTwo);
		jp1.add(jl10);
		jp1.add(determineButton);

		jFrame.add(jp1);
		JLabel jt1 = new JLabel("                                   ");

		jFrame.add(jt1);

		JLabel jl6 = new JLabel(" ");
		jFrame.add(jl6);

		jPanel = new JPanel(new FlowLayout());
		jPanel.setPreferredSize(new Dimension(535, 300));// 关键代码,设置JPanel的大小
		jLabel = new JLabel(
				"序号              红1               红2               红3               红4               红5               红6               蓝               ");
		jPanel.add(jLabel);
		int t = checknum < 0 || checknum > 50 ? 10 : checknum;
		list = new ArrayList<JTextField>();
		for (int i = 0; i < t; i++) {
			String s = i + ".          ";
			if (i < 10)
				s += ' ';
			JLabel jl8 = new JLabel(s);
			jPanel.add(jl8);
			for (int j = 0; j < 7; j++) {
				JTextField jtf = new JTextField(5);
				list.add(jtf);
				jPanel.add(jtf);
			}
			JLabel jl = new JLabel("     ");
			jPanel.add(jl);
		}
		ScrollPane js = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		js.add(jPanel);
		js.setSize(560, 325);
		jFrame.add(js);

		buttonSave = new JButton("保存");
		jFrame.add(buttonSave);

		JPanel jp3 = new JPanel(new FlowLayout());
		jp3.setPreferredSize(new Dimension(540, 150));

		viewArea = new JTextArea(6, 48);
		viewArea.setEditable(false);
		viewArea.setLineWrap(true); 
		
		
		
//		ScrollPane js1 = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
//		js1.add(viewArea);
//		js1.setSize(515, 115);
		
		
		jp3.add(viewArea);
		JLabel jl9 = new JLabel("消息");

		sendTextField = new JTextField(15);

		sendButton = new JButton("发送");

		jp3.add(jl9);
		jp3.add(sendTextField);
		jp3.add(sendButton);
		jFrame.add(jp3);
	}

	public JTextArea getViewArea() {
		return viewArea;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public void initial() {
		isCheck = false;
		name = "无姓名";
		checkTextField.setText("");
		nameTextField.setText("");
		buttonGroup.setSelected(selectOne.getModel(), true);
		isChangeSelectOne = true;
		list = new ArrayList<JTextField>();
		jPanel.removeAll();
		jPanel.updateUI();
		jPanel.add(jLabel);
		preCheckNum = checknum;
		list = new ArrayList<JTextField>();
		preCheckNum = 10;
		for (int i = 0; i < preCheckNum; i++) {
			String s = i + ".          ";
			if (i < 10)
				s += ' ';
			JLabel jl8 = new JLabel(s);
			jPanel.add(jl8);
			for (int j = 0; j < 7; j++) {
				JTextField jtf = new JTextField(5);
				jPanel.add(jtf);
				list.add(jtf);
			}
			JLabel jl = new JLabel("     ");
			jPanel.add(jl);
		}
		jPanel.setPreferredSize(new Dimension(535, 300 + (27 * (preCheckNum - 10))));
		jPanel.repaint();
		jl3.setText("投注期数:   " + sdf.format(d) + (++cnt));
	}

	// 返回0代表没错、返回1代表空、-1代表输入错误、2输入超出范围
	public int judgeCheckNum() {
		if (checkTextField.getText().equals("")) {
			return 1;
		} else {
			try {
				checknum = Integer.parseInt(checkTextField.getText());
			} catch (Exception e1) {
				return -1;
			}
			if (checknum > 50 || checknum < 1) {
				return 2;
			} else {
				if (preCheckNum != checknum) {
					jPanel.removeAll();
					jPanel.updateUI();
					jPanel.add(jLabel);
					preCheckNum = checknum;
					list = new ArrayList<JTextField>();
					for (int i = 0; i < preCheckNum; i++) {
						String s = i + ".          ";
						if (i < 10)
							s += ' ';
						JLabel jl8 = new JLabel(s);
						jPanel.add(jl8);
						for (int j = 0; j < 7; j++) {
							JTextField jtf = new JTextField(5);
							
							jPanel.add(jtf);
							list.add(jtf);
						}
						JLabel jl = new JLabel("     ");
						jPanel.add(jl);
					}
					jPanel.setPreferredSize(new Dimension(535, 300 + (27 * (preCheckNum - 10))));
					jPanel.repaint();
					if(buttonGroup.isSelected(selectOne.getModel())) isChangeSelectOne = true;
					else if(buttonGroup.isSelected(selectTwo.getModel())) isChangeSelectTwo = true;
				}
			}
		}
		return 0;
	}

	public int checkName() {
		if (nameTextField.getText().length() > 10) {
			return 2;
		} else if (nameTextField.getText().equals("")) {
			name = "无姓名";
			return 1;
		} else {
			name = nameTextField.getText();
		}
		return 0;
	}

	public void SelectInput() {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		if (buttonGroup.isSelected(selectOne.getModel()) && isChangeSelectOne) {
			isChangeSelectOne = false;
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setEditable(false);
				int num = 0;
				if ((i + 1) % 7 == 0) {
					do {
						num = (int) ((Math.random() * 16) + 1);
					} while (hashSet.contains(num));
					list.get(i).setText(String.valueOf(num));
					hashSet.clear();
				} else {
					do {
						num = (int) ((Math.random() * 33) + 1);
					} while (hashSet.contains(num));
					list.get(i).setText(String.valueOf(num));
					hashSet.add(num);
				}
			}
		} else if (buttonGroup.isSelected(selectTwo.getModel()) && isChangeSelectTwo) {
			isChangeSelectTwo = false;
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setEditable(true);
				list.get(i).setText("");
			}
		}
	}

	// 3表示重复
	public int judgePerNum() {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equals(""))
				return 1;
			int temp = 0;
			try {
				temp = Integer.parseInt(list.get(i).getText());
			} catch (NumberFormatException e1) {
				return -1;
			}
			if (hashSet.contains(temp))
				return 3;
			hashSet.add(temp);
			if (((i + 1) % 7 == 0) && (temp < 1 || temp > 16) || ((i + 1) % 7 != 0) && (temp < 1 || temp > 33)) {
				return 2;
			}
			if ((i + 1) % 7 == 0)
				hashSet.clear();
		}
		return 0;
	}

}
