package com.devops.springboot_app.controller;

import com.devops.springboot_app.dto.ApiResponse;
import com.devops.springboot_app.entity.Employee;
import com.devops.springboot_app.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ApiResponse<Employee> createEmployee(
            @RequestBody Employee employee) {

        logger.info("Creating Employee : {}", employee.getEmployeeId());

        Employee savedEmployee =
                employeeService.createEmployee(employee);

        return new ApiResponse<>(
                true,
                "Employee created successfully",
                savedEmployee
        );
    }

    @GetMapping
    public ApiResponse<List<Employee>> getAllEmployees() {

        logger.info("Fetching all employees");

        return new ApiResponse<>(
                true,
                "Employees fetched successfully",
                employeeService.getAllEmployees()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<Employee> getEmployeeById(
            @PathVariable Long id) {

        logger.info("Fetching employee {}", id);

        return new ApiResponse<>(
                true,
                "Employee fetched successfully",
                employeeService.getEmployeeById(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        logger.info("Updating employee {}", id);

        return new ApiResponse<>(
                true,
                "Employee updated successfully",
                employeeService.updateEmployee(id, employee)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEmployee(
            @PathVariable Long id) {

        logger.info("Deleting employee {}", id);

        employeeService.deleteEmployee(id);

        return new ApiResponse<>(
                true,
                "Employee deleted successfully",
                null
        );
    }
}