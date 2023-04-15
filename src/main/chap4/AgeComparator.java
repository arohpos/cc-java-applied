package main.chap4;

import java.util.Comparator;

public class AgeComparator implements Comparator<Employee>{
	
	@Override
	public int compare(Employee employee, Employee anotherEmployee) {
		return employee.getAge() - anotherEmployee.getAge();
	}

}
