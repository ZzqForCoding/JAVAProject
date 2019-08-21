package 客户信息管理软件;

public class CustomerView {
	private CustomerList customerList;
	private static boolean loop;
	
	public CustomerView() {
		customerList = new CustomerList(10);
		loop = true;
	}
	
	private void enterMainMenu() throws Exception {
		System.out.println("-----------------客户信息管理软件-----------------\n");
		System.out.println("                   1 添 加 客 户");
		System.out.println("                   2 修 改 客 户");
		System.out.println("                   3 删 除 客 户");
		System.out.println("                   4 客 户 列 表");
		System.out.println("                   5 退          出\n");
		System.out.print("                   请选择(1-5): ");
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
			System.out.println("输入错误");
		}
	}
	
	private void addNewCustomer() {
		System.out.println("---------------------添加客户---------------------");
		System.out.print("姓名: ");
		String name = CMUtility.readString(5);
		
		System.out.print("性别: ");
		char gender = CMUtility.readChar();
		
		System.out.print("年龄: ");
		int age = CMUtility.readInt();
		
		System.out.print("电话: ");
		String phone = CMUtility.readString(11);
		
		System.out.print("邮箱: ");
		String email = CMUtility.readString(20);
		
		customerList.addCustomer(new Customer(name, gender, age, phone, email));
		System.out.println("---------------------添加完成---------------------");
		System.out.println();
	}
	
	private void modifyCustomer() throws Exception {
		System.out.println("---------------------修改客户---------------------");
		System.out.print("请选择待修改客户编号(-1退出): ");
		int n = CMUtility.readInt();
		if(n == -1) {
			System.out.println();
			return;
		} else {
			String preName = customerList.getCustomer(n).getName();
			System.out.print("姓名(" + preName + "): ");
			String name = CMUtility.readString(5, preName);
			
			char preGender = customerList.getCustomer(n).getGender();
			System.out.print("性别(" + preGender + "): ");
			char gender = CMUtility.readChar(preGender);
			
			int preAge = customerList.getCustomer(n).getAge();
			System.out.print("年龄(" + preAge + "): ");
			int age = CMUtility.readInt(preAge);
			
			String prePhone = customerList.getCustomer(n).getPhone();
			System.out.print("电话(" + prePhone + "): ");
			String phone = CMUtility.readString(11, prePhone);
			
			String preEmail = customerList.getCustomer(n).getEmail();
			System.out.print("邮箱(" + preEmail + "): ");
			String email = CMUtility.readString(20, preEmail);
			
			customerList.replaceCustomer(n, new Customer(name, gender, age, phone, email));
			System.out.println("---------------------修改完成---------------------");
			System.out.println();
		}
	}
	
	private void deleteCustomer() {
		System.out.println("---------------------删除客户---------------------");
		System.out.print("请选择待删除客户编号(-1退出): ");
		int n = CMUtility.readInt();
		if(n == -1 || customerList.getTotal() == 0) {
			System.out.println();
			return;
		} else {
			System.out.println("确认是否删除(Y/N): ");
			char c = CMUtility.readConfirmSelection();
			if(c == 'Y') customerList.deleteCustomer(n);
			System.out.println("---------------------删除完成---------------------");
			System.out.println();
		}
	}
	
	private void listAllCustomers() {
		if(customerList.getTotal() == 0) return;
		System.out.println("---------------------------客户列表---------------------------");
		System.out.println("编号    姓名      性别     年龄    电话                   邮箱");
		Customer[] customers = customerList.getAllCustomers();
		int n = 1;
		for (Customer customer : customers) {
			if(customer == null) break;
			System.out.println(n++ + "     " + customer);
		}
		System.out.println("-------------------------客户列表完成-------------------------");
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
