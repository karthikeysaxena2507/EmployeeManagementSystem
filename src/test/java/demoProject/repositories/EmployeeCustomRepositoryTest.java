package demoProject.repositories;

import demoProject.models.Department;
import demoProject.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeCustomRepositoryTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    private EmployeeRepositoryImpl employeeRepositoryImpl;

    private Employee employee;

    private Department department;

    @BeforeEach
    void init() {
        employeeRepositoryImpl = new EmployeeRepositoryImpl(employeeRepository, departmentRepository);
        List<Employee> employees = new ArrayList<>();
        department = new Department(1L, "A", employees);
        employee = new Employee(1L, "B", department);
    }

    @Test
    @DisplayName("test Get All Employees Method")
    public void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> actualEmployees = employeeRepositoryImpl.getAllEmployees();
        Assertions.assertEquals(employees.size(), actualEmployees.size());
    }

    @Test
    @DisplayName("test Get Employee By Id")
    public void testGetEmployeeById() {
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee actualEmployee = employeeRepositoryImpl.getEmployeeById(1L);
        Assertions.assertEquals(employee.getEmployeeId(), actualEmployee.getEmployeeId());
    }

    @Test
    @DisplayName("test Get Employee By Name")
    public void testGetEmployeeByName() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Mockito.when(employeeRepository.findByEmployeeName("B")).thenReturn(employees);
        List<Employee> savedEmployees = employeeRepositoryImpl.getEmployeeByName("B");
        Assertions.assertEquals(employees.size(), savedEmployees.size());
    }

    @Test
    @DisplayName("test find employee by department Id")
    public void testFindEmployeesByDepartmentId() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> savedEmployees = employeeRepositoryImpl.findEmployeesByDepartmentId(1L);
        Assertions.assertEquals(employees.size(), savedEmployees.size());
    }

    @Test
    @DisplayName("test add employee")
    public void testAddEmployee() {
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        employeeRepositoryImpl.addEmployee(employee);
        Mockito.verify(departmentRepository, Mockito.times(1)).save(ArgumentMatchers.any(Department.class));
    }

    @Test
    @DisplayName("test update employee")
    public void testUpdateEmployee() {
        Employee updatedEmployee = new Employee(1L, "C", department);
        Mockito.when(employeeRepository.existsById(1L)).thenReturn(true);
        Mockito.when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);
        Employee actualEmployee = employeeRepositoryImpl.updateEmployee(updatedEmployee);
        Assertions.assertEquals(updatedEmployee.getEmployeeName(), actualEmployee.getEmployeeName());
    }

    @Test
    @DisplayName("test delete employee")
    public void testDeleteEmployee() {
        Mockito.when(employeeRepository.existsById(1L)).thenReturn(true);
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        employeeRepositoryImpl.deleteEmployee(1L);
        Mockito.verify(departmentRepository, Mockito.times(1)).save(ArgumentMatchers.any(Department.class));
    }

}
