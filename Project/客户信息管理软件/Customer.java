package 客户信息管理软件;

public class Customer {
	private String name;
	private char gender;
	private int age;
	private String phone;
	private String email;

	public Customer(String name, char gender, int age, String phone, String email) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return name + "   " +  gender + "         " +  age + "    " +  phone + "   " +  email;
	}

	public static void main(String[] args) {
		Customer customer = new Customer("zzq", '男', 19, "17687952609", "17687952609@163.com");
		System.out.println(customer.getName() + " " + customer.getGender() + " " + customer.getAge() + " " + customer.getPhone() + " " + customer.getEmail());
		customer.setName("hm");
		customer.setGender('女');
		customer.setAge(20);
		customer.setPhone("18897963379");
		customer.setEmail("不知道");
		System.out.println(customer.getName() + " " + customer.getGender() + " " + customer.getAge() + " " + customer.getPhone() + " " + customer.getEmail());
	}
	
}
