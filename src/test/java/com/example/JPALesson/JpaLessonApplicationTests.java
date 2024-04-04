package com.example.JPALesson;

import com.example.JPALesson.entity.Employee;
import com.example.JPALesson.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.encrypt.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class JpaLessonApplicationTests {
	@Autowired
	private EmployeeRepository employeeRepository;

	// Testing the findById
	@Test
	public void testFInById() {
		Employee employee = new Employee();
		Employee newEmployee = employeeRepository.save(employee);
		if (employeeRepository.findById(newEmployee.getEmpId()).isPresent()) {
			Employee employeeResult = employeeRepository.findById(newEmployee.getEmpId()).get();
			assertEquals(employee.getEmpId(), employeeResult.getEmpId());
		} else {
			throw new IllegalStateException("Employee id not found");
		}
	}

	// Test to find all employees
	@Test
	public void testFindAll() {
		Employee employee = new Employee();
		employeeRepository.save(employee);
		List<Employee> employeeList = new ArrayList<>();
		employeeRepository.findAll().forEach(employeeList::add);
		assertEquals(employeeList.size(), 1);
	}

	// Test to save an employee
	@Test
	public void testSaveNewEmployee() {
		Employee employee = new Employee();
		Employee newEmployee = employeeRepository.save(employee);
		if (employeeRepository.findById(newEmployee.getEmpId()).isPresent()) {
			Employee result = employeeRepository.findById(newEmployee.getEmpId()).get();
			assertEquals(employee.getEmpId(), result.getEmpId());
		} else {
			throw new IllegalStateException("Employee not saved");
		}
	}

	// Test the Argon2 Password encryption
	@Test
	public void testArgon2PasswordEncryption() {
		String pass = "password";
		Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(
				16,
				32,
				1,
				60000,
				10
		);
		var encryptedPass = argon2PasswordEncoder.encode(pass);
		assertTrue(argon2PasswordEncoder.matches(pass, encryptedPass));
	}
}
