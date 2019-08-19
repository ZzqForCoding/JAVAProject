package ��ͥ�������;

import java.util.ArrayList;
import java.util.Iterator;

public class FamilyAccount {
	private ArrayList<String> detail;
	private int total = 10000;
	
	public static void main(String[] args) {
		boolean loop = true;
		FamilyAccount familyAccount = new FamilyAccount();
		while (loop) {
			System.out.println("-----------------��ͥ��֧�������-----------------");
			System.out.println("                    1 ��֧��ϸ");
			System.out.println("                    2 �Ǽ�����");
			System.out.println("                    3 �Ǽ�֧��");
			System.out.println("                    4 ��    ��");
			System.out.println();
			System.out.print("                    ��ѡ��(1-4): ");
			char opt = Utility.readMenuSelection();
			System.out.println();
			switch (opt) {
			case '1':
				familyAccount.showDetail();
				break;
			case '2':
				System.out.print("���������� : ");
				int m1 = Utility.readNumber();
				System.out.print("��������˵�� : ");
				String m2 = Utility.readString();
				familyAccount.income(m1, m2);
				break;
			case '3':
				System.out.print("����֧����� : ");
				m1 = Utility.readNumber();
				System.out.print("����֧��˵�� : ");
				m2 = Utility.readString();
				familyAccount.expense(m1, m2);
				break;
			case '4':
				System.out.println("ȷ���Ƿ��˳�(Y/N) : ");
				char c = Utility.readConfirmSelection();
				if(c == 'Y') loop = false;
				break;
			default:
				System.out.println("�������");
				break;
			}
			System.out.println();
		}
	}
	
	public FamilyAccount() {
		detail = new ArrayList<String>();
	}
	
	public void showDetail() {
		Iterator<String> it = detail.iterator();
		System.out.println("-----------------��ǰ��֧��ϸ��¼-----------------");
		System.out.println("��֧         �˻����        ��֧���             ˵��");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("-------------------------------------------------");
	}

	public void income(int m1, String m2) {
		total += m1;
		detail.add("����         " + total + "        " + m1  + "             " + m2);
	}
	
	public void expense(int m1, String m2) {
		total -= m1;
		detail.add("֧��         " + total + "        " + m1  + "             " + m2);
	}
	
}
