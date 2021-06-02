package demoProject.services;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import demoProject.models.Employee;
import demoProject.repositories.DepartmentRepository;
import demoProject.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) throws NoSuchElementFoundException {
        return employeeRepository.findById(employeeId).orElseThrow(NoSuchElementFoundException::new);
    }

    public List<Employee> addEmployee(Employee employee) {
        Long departmentId = employee.getDepartment().getDepartmentId();
        logger.info("{}", departmentId);
        Department department = departmentRepository.findById(departmentId).orElseThrow(NoSuchElementFoundException::new);
        List<Employee> employees = department.getEmployees();
        employees.add(employee);
        department.setEmployees(employees);
        logger.info("{}", employees.size());
        departmentRepository.save(department);
        return department.getEmployees();
    }

    public Employee updateEmployee(Employee employee) throws NoSuchElementFoundException {
        if(employeeRepository.existsById(employee.getEmployeeId())) {
            return employeeRepository.save(employee);
        }
        else throw new NoSuchElementFoundException();
    }

    public void deleteEmployee(Long employeeId) throws NoSuchElementFoundException {
        if(employeeRepository.existsById(employeeId)) {
            Employee employee = employeeRepository.findById(employeeId).orElseThrow(NoSuchElementFoundException::new);
            Long departmentId = employee.getDepartment().getDepartmentId();
            Department department = departmentRepository.findById(departmentId).orElseThrow(NoSuchElementFoundException::new);
            List<Employee> employees = department.getEmployees();
            employees.removeIf(e -> e.getEmployeeId().equals(employee.getEmployeeId()));
            department.setEmployees(employees);
            departmentRepository.save(department);
        }
        else throw new NoSuchElementFoundException();
    }

}
