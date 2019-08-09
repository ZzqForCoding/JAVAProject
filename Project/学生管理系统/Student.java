package 学生管理系统;

public class Student {
	private String code;
	private String name;
	private int age;
	private int score;
	private boolean sex;	//ture男
	
	public Student(String code, String name, int age, int score, boolean sex) throws Exception {
		super();
		if(score < 0 || score > 100) throw new Exception("分数输入错误");
		this.code = code;
		this.name = name;
		this.age = age;
		this.score = score;
		this.sex = sex;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getScore() {
		return score;
	}
	
	public boolean GetSex() {
		return sex;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		String s = sex ? "男" : "女";
		return "Student [code=" + code + ", name=" + name + ", age=" + age + ", score=" + score + ", sex=" + s + "]";
	}
	
}
