package demoProject.repositories;

import demoProject.models.Department;
import demoProject.models.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTestEmbedded {

    @Autowired
    private EmployeeRepository employeeRepository;


}
