package demoProject.repositories;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import demoProject.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    /** @Lazy => Helps to break cyclic bean dependency (loads only an instance of the bean from IOC, not the whole bean **/
    @Autowired
    public EmployeeRepositoryImpl(@Lazy EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;

    }

    Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) throws NoSuchElementFoundException {
        return employeeRepository.findById(employeeId).orElseThrow(NoSuchElementFoundException::new);
    }

    public Employee getEmployeeByName(String employeeName) {
        return employeeRepository.findByEmployeeName(employeeName);
    }

    public Employee addEmployee(Employee employee) {
        Long departmentId = employee.getDepartment().getDepartmentId();
        logger.info("{}", departmentId);
        Department department = departmentRepository.findById(departmentId).orElseThrow(NoSuchElementFoundException::new);
        List<Employee> employees = department.getEmployees();
        employees.add(employee);
        department.setEmployees(employees);
        logger.info("{}", employees.size());
        departmentRepository.save(department);
        return employee;
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

    public List<Employee> findEmployeesByDepartmentId(Long departmentId) {
        List<Employee> employees = employeeRepository.findAll();
        employees.stream().filter(e -> e.getDepartment().getDepartmentId().equals(departmentId)).collect(Collectors.toList());
        return employees;
    }
}
