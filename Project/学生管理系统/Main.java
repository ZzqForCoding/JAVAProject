package 学生管理系统;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean loop = true;
		judge list = new judge();
		System.out.println("这是学生管理系统,希望您满意:");
		while (loop) {
			String ke = in.next();
			switch (ke) {
			case "a":
				System.out.println("请输入: (格式: 学号 姓名 年龄 分数 性别)");
				String code = in.next();
				String name = in.next();
				int age = in.nextInt();
				int score = in.nextInt();
				String sex = in.next();
				list.add(code, name, age, score, sex);
				break;
			case "s":
				list.show();
				break;
			case "d":
				System.out.println("请输入学号:");
				list.delete(in.next());
				break;
			case "m":
				System.out.println("请输入: (格式: 学号 姓名 年龄 分数 性别)");
				list.modify(in.next(), in.next(), in.nextInt(), in.nextInt(), in.next());
				break;
			case "se":
				System.out.println("请输入需要查看的学号:");
				list.see(in.next());
				break;
			case "so":
				list.sortStudent();
				break;
			case "q":
				loop = false;
				break;
			default:
				System.out.println("输入错误");
			}
		}
	}

}
