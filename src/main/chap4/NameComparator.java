package main.chap4;

import java.util.Comparator;

public class NameComparator implements Comparator<Employee>{
		
	@Override
	public int compare(Employee employee, Employee anotherEmployee) {
		return employee.getName().compareTo(anotherEmployee.getName());
	}
	
}
