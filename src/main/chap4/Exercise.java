package main.chap4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise{

	public static void main(String[] args) {
		
		ArrayList<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(1, "tanaka taro", 20));
		employeeList.add(new Employee(2, "suzuki jiro", 40));
		employeeList.add(new Employee(3, "yamada hanako", 19));
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("ソートする基準を選んでください。[1:社員番号（昇順） 2:社員名（昇順） 3:年齢（降順） 9:終了]");
		int input = scanner.nextInt();
		
		if(input != 9) {
			if(input == 1) {			
				Collections.sort(employeeList, new IdComparator());
			}else if(input == 2) {
				Collections.sort(employeeList, new NameComparator());			
			}else if(input == 3) {
				Collections.sort(employeeList, new AgeComparator());
				Collections.reverse(employeeList);
			}
			
			for(Employee employee : employeeList) {
				System.out.println(employee);
			}
		}
	}

}
