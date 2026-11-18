package com.kiet.employeemanagement;



import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    List<Employee> employees = new ArrayList<>();

    // 1. Add Employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(
            @RequestBody Employee emp) {

        employees.add(emp);

        return new ResponseEntity<>(
                emp, HttpStatus.CREATED);
    }

    // 2. Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {

        return employees;
    }

    // 3. Get Employee By ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(
            @PathVariable int id) {

        for (Employee e : employees) {

            if (e.getId() == id) {
                return ResponseEntity.ok(e);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // 4. Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee emp) {

        for (Employee e : employees) {

            if (e.getId() == id) {

                e.setName(emp.getName());
                e.setDepartment(emp.getDepartment());
                e.setSalary(emp.getSalary());

                return ResponseEntity.ok(e);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // 5. Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id) {

        boolean removed = employees.removeIf(
                e -> e.getId() == id);

        if (removed) {
            return ResponseEntity.ok(
                    "Employee deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found");
    }
}


