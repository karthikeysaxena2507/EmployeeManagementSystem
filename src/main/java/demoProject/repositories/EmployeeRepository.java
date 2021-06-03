package demoProject.repositories;

import demoProject.models.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends EntityRepository<Employee, Long> {
    Employee findByEmployeeName(String employeeName);
}
