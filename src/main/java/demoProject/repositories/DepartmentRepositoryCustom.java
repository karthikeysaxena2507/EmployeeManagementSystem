package demoProject.repositories;

import demoProject.models.Department;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<Department> findDepartmentsByMinNoOfEmployees(Long count);
    Department getDepartmentById(Long departmentId);
    Department addDepartment(Department department);
    Department updateDepartment(Department department);
    Department getDepartmentByName(String departmentName);
    List<Department> getAllDepartments();
    void deleteDepartment(Long departmentId);
}
