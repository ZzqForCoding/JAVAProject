package 客户信息管理软件;

public class CustomerList {
	private Customer[] customers;
	private int total;

	public CustomerList(int totalCustomer) {
		super();
		customers = new Customer[totalCustomer];
		this.total = 0;
	}
	
	public boolean addCustomer(Customer customer) {
		if(total < customers.length) {
			customers[total++] = customer;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean replaceCustomer(int index, Customer customer) {
		if(index < 1 || index > total) {
			return false;
		}
		customers[index - 1] = customer;
		return true;
	}
	
	public boolean deleteCustomer(int index) {
		if(index < 1 || index > total) {
			return false;
		}
		for(int i = index - 1; i < total; i++) {
			customers[i] = customers[i + 1];
		}
		total--;
		return true;
	}
	
	public Customer[] getAllCustomers() {
		return customers;
	}
	
	public Customer getCustomer(int index) throws Exception {
		if(index < 1 || index > total) {
			throw new Exception("下标错误\n");
		}
		return customers[index - 1];
	}
	
	public int getTotal() {
		return total;
	}
	
	public static void main(String[] args) {
		CustomerList list = new CustomerList(5);
		System.out.println(list.replaceCustomer(6, new Customer("zzq", '男', 19, "17687952609", "17687952609@163.com")));
//		list.addCustomer(new Customer("zzq", '男', 19, "17687952609", "17687952609@163.com"));
//		list.addCustomer(new Customer("hm", '女', 20, "18897963379", "不知道"));
//		list.addCustomer(new Customer("zzq", '男', 19, "17687952609", "17687952609@163.com"));
//		list.addCustomer(new Customer("hm", '女', 20, "18897963379", "不知道"));
//		list.addCustomer(new Customer("zzq", '男', 19, "17687952609", "17687952609@163.com"));
//		list.addCustomer(new Customer("hm", '女', 20, "18897963379", "不知道"));		
//		Customer[] l = list.getAllCustomers();
//		for (Customer customer : l) {
//			System.out.println(customer);
//		}
//		System.out.println(list.getTotal());
//		try {
//			System.out.println(list.getCustomer(1));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(list.deleteCustomer(0));
//		System.out.println(list.replaceCustomer(0, new Customer("12", '3', 1, "342", "23")));
//		try {
//			System.out.println(list.getCustomer(0));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}
	
}
