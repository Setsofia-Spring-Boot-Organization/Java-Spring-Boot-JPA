package com.example.JPALesson.service;

import com.example.JPALesson.entity.Employee;
import com.example.JPALesson.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    // Initialize the employee repository
    final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get or retrieve an employee using his/her ID
    public Employee getEmployee(int id) {
        Optional<Employee> isEmployeeIdFound = employeeRepository.findById(id);
        if (isEmployeeIdFound.isEmpty()) throw new IllegalStateException("Employee not found");
        return isEmployeeIdFound.get();
    }

    // Return all available Employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    // Save new employee or Update an old one
    public Employee saveOrUpdateEmployee(Employee employee) {
       return employeeRepository.save(employee);
    }

    // Delete and employee using his/her ID
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}