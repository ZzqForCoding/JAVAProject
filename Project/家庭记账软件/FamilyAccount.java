package 家庭记账软件;

import java.util.ArrayList;
import java.util.Iterator;

public class FamilyAccount {
	private ArrayList<String> detail;
	private int total = 10000;
	
	public static void main(String[] args) {
		boolean loop = true;
		FamilyAccount familyAccount = new FamilyAccount();
		while (loop) {
			System.out.println("-----------------家庭收支记账软件-----------------");
			System.out.println("                    1 收支明细");
			System.out.println("                    2 登记收入");
			System.out.println("                    3 登记支出");
			System.out.println("                    4 退    出");
			System.out.println();
			System.out.print("                    请选择(1-4): ");
			char opt = Utility.readMenuSelection();
			System.out.println();
			switch (opt) {
			case '1':
				familyAccount.showDetail();
				break;
			case '2':
				System.out.print("本次收入金额 : ");
				int m1 = Utility.readNumber();
				System.out.print("本次收入说明 : ");
				String m2 = Utility.readString();
				familyAccount.income(m1, m2);
				break;
			case '3':
				System.out.print("本次支出金额 : ");
				m1 = Utility.readNumber();
				System.out.print("本次支出说明 : ");
				m2 = Utility.readString();
				familyAccount.expense(m1, m2);
				break;
			case '4':
				System.out.println("确认是否退出(Y/N) : ");
				char c = Utility.readConfirmSelection();
				if(c == 'Y') loop = false;
				break;
			default:
				System.out.println("输入错误");
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
		System.out.println("-----------------当前收支明细记录-----------------");
		System.out.println("收支         账户金额        收支金额             说明");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("-------------------------------------------------");
	}

	public void income(int m1, String m2) {
		total += m1;
		detail.add("收入         " + total + "        " + m1  + "             " + m2);
	}
	
	public void expense(int m1, String m2) {
		total -= m1;
		detail.add("支出         " + total + "        " + m1  + "             " + m2);
	}
	
}
