package demoProject.repositories;

import demoProject.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends EntityRepository<Employee, Long>, EmployeeRepositoryCustom {
    List<Employee> findByEmployeeName(String employeeName);
}
