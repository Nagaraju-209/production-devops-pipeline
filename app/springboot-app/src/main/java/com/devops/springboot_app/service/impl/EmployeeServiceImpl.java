package com.devops.springboot_app.service.impl;

import com.devops.springboot_app.dto.EmployeeRequest;
import com.devops.springboot_app.dto.EmployeeResponse;
import com.devops.springboot_app.entity.Employee;
import com.devops.springboot_app.exception.DuplicateEmployeeException;
import com.devops.springboot_app.exception.EmployeeNotFoundException;
import com.devops.springboot_app.mapper.EmployeeMapper;
import com.devops.springboot_app.repository.EmployeeRepository;
import com.devops.springboot_app.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository,
                               EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {

        logger.info("Creating employee with employeeId: {}", request.getEmployeeId());

        if (repository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmployeeException(
                    "Email",
                    request.getEmail());
        }

        if (repository.existsByEmployeeId(request.getEmployeeId())) {
            throw new DuplicateEmployeeException(
                    "Employee ID",
                    request.getEmployeeId());
        }

        Employee employee = mapper.toEntity(request);

        Employee savedEmployee = repository.save(employee);

        logger.info("Employee created successfully with ID: {}", savedEmployee.getId());

        return mapper.toResponse(savedEmployee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {

        logger.info("Fetching all employees");

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {

        logger.info("Fetching employee with ID: {}", id);

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(id));

        return mapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id,
                                           EmployeeRequest request) {

        logger.info("Updating employee with ID: {}", id);

        Employee existing = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(id));

        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setDepartment(request.getDepartment());
        existing.setDesignation(request.getDesignation());
        existing.setSalary(request.getSalary());

        Employee updatedEmployee = repository.save(existing);

        logger.info("Employee updated successfully with ID: {}", updatedEmployee.getId());

        return mapper.toResponse(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        logger.info("Deleting employee with ID: {}", id);

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(id));

        repository.delete(employee);

        logger.info("Employee deleted successfully with ID: {}", id);
    }
}