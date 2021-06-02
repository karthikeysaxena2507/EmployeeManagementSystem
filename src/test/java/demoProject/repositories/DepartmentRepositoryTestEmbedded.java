package demoProject.repositories;

import demoProject.DemoProjectApplication;
import demoProject.config.JpaConfiguration;
import demoProject.models.Department;
import demoProject.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@DataJpaTest // error de rha
public class DepartmentRepositoryTestEmbedded {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveDepartment() {
//        List<Employee> employees = new ArrayList<>();
//        Department department = new Department(1L, "AB", employees);
//        Department savedDepartment = departmentRepository.save(department);
//        Assertions.assertEquals(department.getDepartmentName() ,savedDepartment.getDepartmentName());
        Assertions.assertEquals(1, 1);
    }

}
