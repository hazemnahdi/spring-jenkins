package com.alpha.pointage.controller;


import com.alpha.pointage.dto.EmployeeDTO;
import com.alpha.pointage.entity.EmployeeEntity;
import com.alpha.pointage.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/add")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee) {
        EmployeeEntity createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> countTotalEmployees() {
        long totalEmployees = employeeService.getTotalEmployees();
        return new ResponseEntity<>(totalEmployees, HttpStatus.OK);
    }
    @GetMapping("allemployee")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/average-salary")
    public ResponseEntity<Double> getAverageSalary() {
        Double moyenne= employeeService.calculateAverageSalary();
        return new ResponseEntity<>(moyenne,HttpStatus.OK);
    }
    @GetMapping("/min-salary")
    public ResponseEntity<Double> getMinSalary() {
        Double min= employeeService.calculateMinSalary();
        return new ResponseEntity<>(min,HttpStatus.OK);
    }
    @GetMapping("/employees")
    public List<EmployeeEntity> getFirstFiveEmployees() {
        return employeeService.getFirstFiveEmployees();
    }
    @GetMapping("/employeess")
    public List<EmployeeEntity> getLastFiveEmployees() {
        return employeeService.getLastFiveEmployees();
    }
    @GetMapping("/employeesss")
    public List<EmployeeEntity> getEmployeesByName(@RequestParam("name") String name) {
        return employeeService.getEmployeesByName(name);
    }
    @GetMapping("/total-prime")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesWithTotalPrime() {
        List<EmployeeDTO> employeesWithTotalPrime = employeeService.getEmployeesWithTotalPrime();
        return new ResponseEntity<>(employeesWithTotalPrime, HttpStatus.OK);
    }
}
