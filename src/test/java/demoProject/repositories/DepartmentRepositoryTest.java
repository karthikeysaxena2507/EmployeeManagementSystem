package demoProject.repositories;

import demoProject.config.JpaConfiguration;
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
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    public void setup() {
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

}
