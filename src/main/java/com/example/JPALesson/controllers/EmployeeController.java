package com.example.JPALesson.controllers;

import com.example.JPALesson.entity.Employee;
import com.example.JPALesson.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class EmployeeController {
    // Initialize the employee services
    final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all the available employees
    @GetMapping("employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get a single employee
    @GetMapping("employee/{id}")
    public Employee getEmployee(@PathVariable("id") int empId) {
        return employeeService.getEmployee(empId);
    }

    // Add a new employee
    @PostMapping("employee")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        return employeeService.saveOrUpdateEmployee(employee);
    }

    // Update an existing employee
    @PutMapping("employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.saveOrUpdateEmployee(employee);
    }

    // delete an existing employee using his or her id
    @DeleteMapping("employee/{id}")
    public void deleteAnEmployee(@PathVariable("id") int empId) {
        employeeService.deleteEmployee(empId);
    }
}
