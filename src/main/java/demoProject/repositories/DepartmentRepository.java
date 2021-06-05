package demoProject.repositories;

import demoProject.models.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends EntityRepository<Department, Long>, DepartmentRepositoryCustom {
    Department findByDepartmentName(String departmentName);
}
