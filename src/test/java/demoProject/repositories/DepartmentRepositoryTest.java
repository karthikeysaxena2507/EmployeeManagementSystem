package demoProject.repositories;

import demoProject.DemoProjectTests;
import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import demoProject.models.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryTest extends DemoProjectTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    public void setup() {
        departmentRepository.deleteAll();
        List<Employee> employees = new ArrayList<>();
        department = new Department(10L, "B", employees);
        departmentRepository.save(department);
    }
    @AfterEach
    public void clear() {
        if(departmentRepository.existsById(10L)) {
            departmentRepository.deleteById(10L);
        }
    }

    @Test
    @DisplayName("Test save method")
    public void testSaveDepartment() {
        Department savedDepartment = departmentRepository.save(department);
        Assertions.assertEquals(department.getDepartmentName() ,savedDepartment.getDepartmentName());
    }

    @Test
    @DisplayName("Test find By Id method")
    public void testFindById() {
        Department foundDepartment = departmentRepository.findById(10L).orElseThrow(NoSuchElementFoundException::new);
        Assertions.assertEquals(department.getDepartmentName() ,foundDepartment.getDepartmentName());
    }

    @Test
    @DisplayName("Test find All method")
    public void testFindAll() {
        List<Department> departments = departmentRepository.findAll();
        Assertions.assertEquals(1 ,departments.size());
    }

    @Test
    @DisplayName("Test delete By Id")
    public void testDeleteById() {
        departmentRepository.deleteById(10L);
        Assertions.assertTrue(departmentRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test find department by name")
    public void testFindByName() {
        List<Department> departments = departmentRepository.findByDepartmentName("B");
        Assertions.assertEquals(1, departments.size());
    }

    @Test
    @DisplayName("Test exists by id")
    public void testExistsById() {
        Assertions.assertTrue(departmentRepository.existsById(10L));
        Assertions.assertFalse(departmentRepository.existsById(15L));
    }

}
