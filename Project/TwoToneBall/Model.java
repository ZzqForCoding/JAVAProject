package TwoToneBall;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextField;

public class Model implements Serializable {
	private static final long serialVersionUID = 2L;
	private String name;
	private String time;
	private static HashMap<String, ArrayList<ArrayList<Integer>>> hashMap;
	private static ArrayList<String> nameList;


	public Model() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("a.txt"));
			oos.writeObject(this);
			oos.close();
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		initial();
	}

	public void save(String name, String time, ArrayList<JTextField> list) {
		this.time = time;
		this.name = name;
		int n = list.size() / 7;
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> ans = new ArrayList<Integer>();
			for (int j = 0; j < 7; j++) {
				ans.add(Integer.parseInt(list.get(i * 7 + j).getText()));
			}
			ExcuteInsOne(ans);
		}
	}

	// create drop±í
	public void ExcuteCDOne(String s) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lottery?serverTimezone=UTC&characterEncoding=utf-8", "root", "");
			Statement stmt = con.createStatement();
			stmt.execute(s);
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void ExcuteCDTwo(String s) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lottery?serverTimezone=UTC&characterEncoding=utf-8", "root", "");
			Statement stmt = con.createStatement();
			stmt.execute(s);
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// insert
	public void ExcuteInsOne(ArrayList<Integer> list) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lottery?serverTimezone=UTC&characterEncoding=utf-8", "root", "");
			String s = "INSERT INTO Customer(lotTime, customerName, redOne, redTwo, redThree, redFour, redFive, redSix, blue) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(s);
			pstmt.setString(1, time);
			pstmt.setString(2, name);
			for (int i = 0, j = 3; i < list.size() && j < 10; i++, j++) {
				pstmt.setInt(j, list.get(i));
			}
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// insert
	public void ExcuteInsTwo(ArrayList<Integer> list) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lottery?serverTimezone=UTC&characterEncoding=utf-8", "root", "");
			String s = "INSERT INTO lotteryres(lotTime, redOne, redTwo, redThree, redFour, redFive, redSix, blue) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(s);
			pstmt.setString(1, time);
			for (int i = 0, j = 2; i < list.size() && j < 9; i++, j++) {
				pstmt.setInt(j, list.get(i));
			}
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ExcuteSelect() {
		try {
			hashMap = new HashMap<String, ArrayList<ArrayList<Integer>>>();
			nameList = new ArrayList<String>();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lottery?serverTimezone=UTC&characterEncoding=utf-8", "root", "");
			String s = "SELECT id, customerName, redOne, redTwo, redThree, redFour, redFive, redSix, blue FROM customer";
			PreparedStatement pstmt = con.prepareStatement(s);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(2);
				nameList.add(name);
				ArrayList<ArrayList<Integer>> val;
				ArrayList<Integer> list;
				if (hashMap.containsKey(name)) {
					val = hashMap.get(name);
					list = new ArrayList<Integer>();
				} else {
					val = new ArrayList<ArrayList<Integer>>();
					list = new ArrayList<Integer>();
				}
				Set<String> set = new HashSet<String>(nameList);
				nameList = new ArrayList<String>(set);
				list.add(rs.getInt(1));
				list.add(rs.getInt(3));
				list.add(rs.getInt(4));
				list.add(rs.getInt(5));
				list.add(rs.getInt(6));
				list.add(rs.getInt(7));
				list.add(rs.getInt(8));
				list.add(rs.getInt(9));
				val.add(list);
				hashMap.put(name, val);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void initial() {
		ExcuteCDOne("drop table if exists customer");
		ExcuteCDOne("CREATE TABLE IF NOT EXISTS Customer (\r\n" + "	id INT PRIMARY KEY AUTO_INCREMENT,\r\n"
				+ "	lotTime VARCHAR(20),\r\n" + "	customerName VARCHAR(20),\r\n" + "	redOne INT,\r\n"
				+ "	redTwo INT,\r\n" + "	redThree INT,\r\n" + "	redFour INT,\r\n" + "	redFive INT,\r\n"
				+ "	redSix INT,\r\n" + "	blue INT,\r\n" + "	isprize VARCHAR(2),\r\n" + "	prizeLV INT\r\n"
				+ ");");

		ExcuteCDTwo("DROP TABLE IF EXISTS lotteryres");
		ExcuteCDTwo("CREATE TABLE IF NOT EXISTS LotteryRes (\r\n" + "	id INT PRIMARY KEY AUTO_INCREMENT,\r\n"
				+ "	lotTime VARCHAR(20),\r\n" + "	redOne INT,\r\n" + "	redTwo INT,\r\n" + "	redThree INT,\r\n"
				+ "	redFour INT,\r\n" + "	redFive INT,\r\n" + "	redSix INT,\r\n" + "	blue INT\r\n" + ");");
	}
	
	public HashMap<String, ArrayList<ArrayList<Integer>>> getHashMap() {
		return hashMap;
	}

	public ArrayList<String> getNameList() {
		return nameList;
	}
	
}