package ѧ������ϵͳ;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean loop = true;
		judge list = new judge();
		System.out.println("����ѧ������ϵͳ,ϣ��������:");
		while (loop) {
			String ke = in.next();
			switch (ke) {
			case "a":
				System.out.println("������: (��ʽ: ѧ�� ���� ���� ���� �Ա�)");
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
				System.out.println("������ѧ��:");
				list.delete(in.next());
				break;
			case "m":
				System.out.println("������: (��ʽ: ѧ�� ���� ���� ���� �Ա�)");
				list.modify(in.next(), in.next(), in.nextInt(), in.nextInt(), in.next());
				break;
			case "se":
				System.out.println("��������Ҫ�鿴��ѧ��:");
				list.see(in.next());
				break;
			case "so":
				list.sortStudent();
				break;
			case "q":
				loop = false;
				break;
			default:
				System.out.println("�������");
			}
		}
	}

}
