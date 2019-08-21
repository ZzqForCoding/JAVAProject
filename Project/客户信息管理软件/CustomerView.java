package �ͻ���Ϣ�������;

public class CustomerView {
	private CustomerList customerList;
	private static boolean loop;
	
	public CustomerView() {
		customerList = new CustomerList(10);
		loop = true;
	}
	
	private void enterMainMenu() throws Exception {
		System.out.println("-----------------�ͻ���Ϣ�������-----------------\n");
		System.out.println("                   1 �� �� �� ��");
		System.out.println("                   2 �� �� �� ��");
		System.out.println("                   3 ɾ �� �� ��");
		System.out.println("                   4 �� �� �� ��");
		System.out.println("                   5 ��          ��\n");
		System.out.print("                   ��ѡ��(1-5): ");
		char c = CMUtility.readMenuSelection();
		System.out.println();
		if(c == '1') {
			addNewCustomer();
		} else if(c == '2') {
			modifyCustomer();
		} else if(c == '3') {
			deleteCustomer();
		} else if(c == '4') {
			listAllCustomers();
		} else if(c == '5') {
			char s = CMUtility.readConfirmSelection();
			if(s == 'Y') {
				loop = false;		
			}
		} else {
			System.out.println("�������");
		}
	}
	
	private void addNewCustomer() {
		System.out.println("---------------------��ӿͻ�---------------------");
		System.out.print("����: ");
		String name = CMUtility.readString(5);
		
		System.out.print("�Ա�: ");
		char gender = CMUtility.readChar();
		
		System.out.print("����: ");
		int age = CMUtility.readInt();
		
		System.out.print("�绰: ");
		String phone = CMUtility.readString(11);
		
		System.out.print("����: ");
		String email = CMUtility.readString(20);
		
		customerList.addCustomer(new Customer(name, gender, age, phone, email));
		System.out.println("---------------------������---------------------");
		System.out.println();
	}
	
	private void modifyCustomer() throws Exception {
		System.out.println("---------------------�޸Ŀͻ�---------------------");
		System.out.print("��ѡ����޸Ŀͻ����(-1�˳�): ");
		int n = CMUtility.readInt();
		if(n == -1) {
			System.out.println();
			return;
		} else {
			String preName = customerList.getCustomer(n).getName();
			System.out.print("����(" + preName + "): ");
			String name = CMUtility.readString(5, preName);
			
			char preGender = customerList.getCustomer(n).getGender();
			System.out.print("�Ա�(" + preGender + "): ");
			char gender = CMUtility.readChar(preGender);
			
			int preAge = customerList.getCustomer(n).getAge();
			System.out.print("����(" + preAge + "): ");
			int age = CMUtility.readInt(preAge);
			
			String prePhone = customerList.getCustomer(n).getPhone();
			System.out.print("�绰(" + prePhone + "): ");
			String phone = CMUtility.readString(11, prePhone);
			
			String preEmail = customerList.getCustomer(n).getEmail();
			System.out.print("����(" + preEmail + "): ");
			String email = CMUtility.readString(20, preEmail);
			
			customerList.replaceCustomer(n, new Customer(name, gender, age, phone, email));
			System.out.println("---------------------�޸����---------------------");
			System.out.println();
		}
	}
	
	private void deleteCustomer() {
		System.out.println("---------------------ɾ���ͻ�---------------------");
		System.out.print("��ѡ���ɾ���ͻ����(-1�˳�): ");
		int n = CMUtility.readInt();
		if(n == -1 || customerList.getTotal() == 0) {
			System.out.println();
			return;
		} else {
			System.out.println("ȷ���Ƿ�ɾ��(Y/N): ");
			char c = CMUtility.readConfirmSelection();
			if(c == 'Y') customerList.deleteCustomer(n);
			System.out.println("---------------------ɾ�����---------------------");
			System.out.println();
		}
	}
	
	private void listAllCustomers() {
		if(customerList.getTotal() == 0) return;
		System.out.println("---------------------------�ͻ��б�---------------------------");
		System.out.println("���    ����      �Ա�     ����    �绰                   ����");
		Customer[] customers = customerList.getAllCustomers();
		int n = 1;
		for (Customer customer : customers) {
			if(customer == null) break;
			System.out.println(n++ + "     " + customer);
		}
		System.out.println("-------------------------�ͻ��б����-------------------------");
		System.out.println("");
	}
	
	public static void main(String[] args) {
		CustomerView customerView = new CustomerView();

		while(loop) {
			try {
				customerView.enterMainMenu();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}
