package com.satybaev.spring.rest.controller;

import com.satybaev.spring.rest.entity.Employee;
import com.satybaev.spring.rest.exception_handling.EmployeeIncorrectData;
import com.satybaev.spring.rest.exception_handling.NoSuchEmployeeException;
import com.satybaev.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
       return checkEmployee(id);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        checkEmployee(id);
        employeeService.deleteEmployee(id);
        return "Employee with ID " + id + " was deleted";
    }

    private Employee checkEmployee(int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException(
                    "There is no employee with ID = " +
                            id + " in Database");
        }
        return employee;
    }
}







