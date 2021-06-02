package demoProject.services;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import demoProject.models.Employee;
import demoProject.repositories.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTests {

    Logger logger = LoggerFactory.getLogger(DepartmentServiceTests.class);

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentService departmentService;

    private Department expectedDepartment;

    @BeforeEach()
    public void init() {
        departmentService = new DepartmentService(departmentRepository);
        List<Employee> employees = new ArrayList<>();
        expectedDepartment = new Department(1L, "AB", employees);
        employees.add(new Employee("A", expectedDepartment));
        employees.add(new Employee("B", expectedDepartment));
        expectedDepartment.setEmployees(employees);
    }

    @Test
    @DisplayName("Test Get Department By Id")
    public void testGetDepartmentById() throws NoSuchElementFoundException {

        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(expectedDepartment));

        Department actualDepartment = departmentService.getDepartmentById(1L);

        Assertions.assertEquals(expectedDepartment.getDepartmentId(), actualDepartment.getDepartmentId());
    }

    @Test
    @DisplayName("Test Get All Departments")
    public void testGetAllDepartments() {

        List<Department> departments = new ArrayList<>();
        departments.add(expectedDepartment);

        Mockito.when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> actualDepartments = departmentService.getAllDepartments();

        Assertions.assertEquals(departments.size(), actualDepartments.size());
    }

    @Test
    @DisplayName("Test Add Department Method - 1")
    public void testAddDepartment1() {

        Mockito.when(departmentRepository.save(expectedDepartment)).thenReturn(expectedDepartment);

        Department actualDepartment = departmentService.addDepartment(expectedDepartment);

        Assertions.assertEquals(expectedDepartment, actualDepartment);
    }

    @Test
    @DisplayName("Test Add Department Method - 2")
    public void testAddDepartment2() {

        departmentService.addDepartment(expectedDepartment);

        Mockito.verify(departmentRepository, Mockito.times(1)).save(ArgumentMatchers.any(Department.class));
    }

    @Test
    @DisplayName("Test Delete Department Method")
    public void testDeleteDepartmentById() throws NoSuchElementFoundException {

        departmentService.deleteDepartment(1L);

        Mockito.verify(departmentRepository, Mockito.times(1)).existsById(1L);

    }

    @Test
    @DisplayName("Test Update Department Method")
    public void testUpdateDepartmentMethod() throws NoSuchElementFoundException {

        List<Employee> employees = new ArrayList<>();
        Department newDepartment = new Department(1L, "ABC", employees);
        employees.add(new Employee("A", newDepartment));
        employees.add(new Employee("B", newDepartment));
        employees.add(new Employee("C", newDepartment));
        newDepartment.setEmployees(employees);

        Mockito.when(departmentRepository.save(newDepartment)).thenReturn(newDepartment);

        Assertions.assertEquals(newDepartment, departmentService.updateDepartment(newDepartment));

    }

}
