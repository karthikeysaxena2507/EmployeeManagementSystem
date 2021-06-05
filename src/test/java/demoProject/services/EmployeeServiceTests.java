package demoProject.services;

import demoProject.models.Department;
import demoProject.models.Employee;
import demoProject.repositories.DepartmentRepository;
import demoProject.repositories.EmployeeRepository;
import demoProject.repositories.EmployeeRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests  {

//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @Mock
//    private DepartmentRepository departmentRepository;
//
//    private EmployeeRepositoryImpl employeeService;
//
//    private Employee employee;
//
//    private Department department;
//
//    @BeforeEach
//    void init() {
//        employeeService = new EmployeeRepositoryImpl(employeeRepository, departmentRepository);
//        List<Employee> employees = new ArrayList<>();
//        department = new Department(1L, "A", employees);
//        employee = new Employee(1L, "B", department);
//    }
//
//    @Test
//    @DisplayName("test Get All Employees Method")
//    public void testGetAllEmployees() {
//        List<Employee> employees = new ArrayList<>();
//        employees.add(employee);
//        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
//        List actualEmployees = employeeService.getAllEmployees();
//        Assertions.assertEquals(employees.size(), actualEmployees.size());
//    }
//
//    @Test
//    @DisplayName("test Get Employee By Id")
//    public void testGetEmployeeById() {
//        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
//        Employee actualEmployee = employeeService.getEmployeeById(1L);
//        Assertions.assertEquals(employee.getEmployeeId(), actualEmployee.getEmployeeId());
//    }

}
