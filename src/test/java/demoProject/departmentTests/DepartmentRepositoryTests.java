package demoProject.departmentTests;

import demoProject.models.Department;
import demoProject.models.Employee;
import demoProject.repositories.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentRepositoryTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testRepository() {
        List<Employee> employees = new ArrayList<>();
        Department department = new Department(1L, "AB", employees);
        employees.add(new Employee("A", department));
        employees.add(new Employee("B", department));
        department.setEmployees(employees);

        departmentRepository.save(department);

        List<Department> departments = departmentRepository.findAll();

        Assertions.assertEquals(1, departments.size());
    }


}
