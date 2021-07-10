package demoProject.repositories;

import demoProject.models.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DepartmentRepository extends EntityRepository<Department, Long>, DepartmentRepositoryCustom {
    List<Department> findByDepartmentName(String departmentName);
}
