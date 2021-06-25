package demoProject.repositories;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import demoProject.models.Employee;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DepartmentCustomRepositoryTest
    extends AbstractTestNGSpringContextTests {


    @Mock
    private DepartmentRepository departmentRepository;

    private Department department;

    @Autowired
    private DepartmentRepositoryImpl departmentRepositoryImpl;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.openMocks(this);
        List<Employee> employees = new ArrayList<>();
        department = new Department(15L, "A", employees);
    }

    @Test
    public void testGetDepartmentById() throws NoSuchElementFoundException {
        Mockito.when(departmentRepository.findById(15L)).thenReturn(Optional.of(department));
        Department actualDepartment = departmentRepositoryImpl.getDepartmentById(15L);
        Assert.assertEquals(department.getDepartmentId(), actualDepartment.getDepartmentId());
    }

//    @Test
//    @DisplayName("Test Get All Departments")
//    public void testGetAllDepartments() {
//        List<Department> departments = new ArrayList<>();
//        departments.add(department);
//        Mockito.when(departmentRepository.findAll()).thenReturn(departments);
//        List<Department> actualDepartments = departmentRepositoryImpl.getAllDepartments();
//        Assertions.assertEquals(departments.size(), actualDepartments.size());
//    }
//
//    @Test
//    @DisplayName("Test Add Department Method - 1")
//    public void testAddDepartment1() {
//        Mockito.when(departmentRepository.save(department)).thenReturn(department);
//        Department actualDepartment = departmentRepositoryImpl.addDepartment(department);
//        Assertions.assertEquals(department, actualDepartment);
//    }
//
//    @Test
//    @DisplayName("Test Add Department Method - 2")
//    public void testAddDepartment2() {
//        departmentRepositoryImpl.addDepartment(department);
//        Mockito.verify(departmentRepository, Mockito.times(1)).save(ArgumentMatchers.any(Department.class));
//    }
//
//    @Test
//    @DisplayName("Test Delete Department Method")
//    public void testDeleteDepartmentById() throws NoSuchElementFoundException {
//        Mockito.when(departmentRepository.existsById(1L)).thenReturn(true);
//        departmentRepositoryImpl.deleteDepartment(1L);
//        Mockito.verify(departmentRepository, Mockito.times(1)).deleteById(1L);
//    }
//
//    @Test
//    @DisplayName("Test Update Department Method")
//    public void testUpdateDepartmentMethod() throws NoSuchElementFoundException {
//        List<Employee> employees = new ArrayList<>();
//        Department newDepartment = new Department(1L, "ABC", employees);
//        employees.add(new Employee("A", newDepartment));
//        newDepartment.setEmployees(employees);
//        Mockito.when(departmentRepository.existsById(1L)).thenReturn(true);
//        Mockito.when(departmentRepository.save(newDepartment)).thenReturn(newDepartment);
//        Assertions.assertEquals(newDepartment, departmentRepositoryImpl.updateDepartment(newDepartment));
//    }
//

}
