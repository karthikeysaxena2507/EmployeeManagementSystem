package demoProject.repositories;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import demoProject.models.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Department department;

    private Employee employee;

    @BeforeEach
    public void setup() {
        List<Employee> employees = new ArrayList<>();
        department = new Department(1L, "A", employees);
        employee = new Employee(1L, "A", department);
        employeeRepository.save(employee);
    }

    @AfterEach
    public void clear() {
        if(employeeRepository.existsById(1L)) {
            employeeRepository.deleteById(1L);
        }
    }

    @Test
    @DisplayName("test Get Employee By Id Method")
    public void testGetEmployeeById() {
        Employee actualEmployee = employeeRepository.findById(1L).orElseThrow(NoSuchElementFoundException::new);
        Assertions.assertEquals(employee.getEmployeeName(), actualEmployee.getEmployeeName());
    }

    @Test
    @DisplayName("test Get All Employees Method")
    public void testGetAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        Assertions.assertEquals(1, employees.size());
    }

    @Test
    @DisplayName("test save Employee Method")
    public void testAddEmployee() {
        Employee savedEmployee = employeeRepository.save(employee);
        Assertions.assertEquals(employee.getEmployeeName(), savedEmployee.getEmployeeName());
    }

    @Test
    @DisplayName("test delete Employee Method")
    public void deleteEmployeeById() {
        employeeRepository.deleteById(1L);
        Assertions.assertTrue(employeeRepository.findAll().isEmpty());
    }

}
