package demoProject.repositories;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentRepositoryImpl implements DepartmentRepositoryCustom {

    private DepartmentRepository departmentRepository;

    /** @Lazy => Helps to break cyclic bean dependency (loads only an instance of the bean from IOC, not the whole bean **/
    public DepartmentRepositoryImpl(@Lazy DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws NoSuchElementFoundException {
        return departmentRepository.findById(departmentId).orElseThrow(NoSuchElementFoundException::new);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }

    public Department updateDepartment(Department department) throws NoSuchElementFoundException {
        if(departmentRepository.existsById(department.getDepartmentId())) {
            return departmentRepository.save(department);
        }
        else throw new NoSuchElementFoundException();
    }

    public void deleteDepartment(Long departmentId) throws NoSuchElementFoundException {
        if(departmentRepository.existsById(departmentId)) {
            departmentRepository.deleteById(departmentId);
        }
        else throw new NoSuchElementFoundException();
    }

    public List<Department> findDepartmentsByMinNoOfEmployees(Long employeeCount) {
        List<Department> departments = departmentRepository.findAll();
        departments.stream().filter(d -> d.getEmployees().size() >= employeeCount).collect(Collectors.toList());
        return departments;
    }
}
