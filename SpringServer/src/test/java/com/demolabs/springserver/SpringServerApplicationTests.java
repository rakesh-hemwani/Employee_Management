package com.demolabs.springserver;

import com.demolabs.springserver.model.Employee;
import com.demolabs.springserver.service.EmployeeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringServerApplicationTests {

	@Autowired
	private EmployeeDao employeeDao;
	@Test
	void addEmployeeTest() {
		Employee employee=new Employee();
		employee.setName("Rakesh");
		employee.setLocation("Building-5");
		employee.setBranch("IT");
		employeeDao.saveEmployee(employee);
	}

	//@Test
	void getAllAndDeleteEmployee(){
		List<Employee> employees=employeeDao.getAllEmployees();
		System.out.println(employees);
		for (Employee employee:employees){
			employeeDao.deleteEmployee(employee);
		}
	}
}
