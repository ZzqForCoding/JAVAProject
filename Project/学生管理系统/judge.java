package 学生管理系统;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class judge {
	private ArrayList<Student> list;
	
	public judge() {
		list = new ArrayList<Student>();
	}
	
	public void add(String code, String name, int age, int score, String sex) {
		try {
			list.add(new Student(code, name, age, score, sex.equals("男") ? true : false ));	
			System.out.println("添加成功");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void show() {
		if(list.isEmpty()) {
			System.out.println("暂无");
			return;
		}
		System.out.println("学生有:");
		Iterator<Student> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());			
		}
	}
	
	public void delete(String code) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getCode().equals(code)) {
				list.remove(i);
				System.out.println("删除成功");
				return;
			}
		}
		System.out.println("未找到");
	}
	
	public void modify(String code, String name, int age, int score, String sex) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getCode().equals(code)) {
				list.get(i).setName(name);
				list.get(i).setAge(age);
				list.get(i).setScore(score);
				list.get(i).setSex(sex.equals("男") ? true : false);
				System.out.println("修改成功");
				return;
			}
		}
		System.out.println("未找到");
	}
	
	public void see(String code) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getCode().equals(code)) {
				System.out.println(list.get(i));
				return;
			}
		}
		System.out.println("未找到");
	}
	
	public void sortStudent() {
		list.sort(new Comparator<Student>() {
			public int compare(Student s1, Student s2) {
				int a = s2.getScore() - s1.getScore();
				int b = a == 0 ? s1.getCode().compareTo(s2.getCode()) : a;
				return b;
			}
		});
		System.out.println("已排序");
	}
	
}
