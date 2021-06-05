package demoProject.repositories;


import demoProject.models.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> findEmployeesByDepartmentId(Long departmentId);
    List<Employee> getAllEmployees();
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(Long employeeId);
    Employee updateEmployee(Employee employee);
    Employee getEmployeeByName(String employeeName);
    void deleteEmployee(Long EmployeeId);
}
