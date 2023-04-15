package main.chap4;

import java.util.Comparator;

public class IdComparator implements Comparator<Employee>{
	
	@Override
	public int compare(Employee employee, Employee anotherEmployee) {
		return employee.getId() - anotherEmployee.getId();
	}

}
