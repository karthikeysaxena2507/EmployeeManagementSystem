package demoProject.repositories;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentRepositoryImpl implements DepartmentRepositoryCustom {

    private DepartmentRepository departmentRepository;

    /** @Lazy => Helps to break cyclic bean dependency (loads only an instance of the bean from IOC, not the whole bean **/
    public DepartmentRepositoryImpl(@Lazy DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getDepartmentById(Long departmentId) throws NoSuchElementFoundException {
        Department department = departmentRepository.findById(departmentId).orElseThrow(NoSuchElementFoundException::new);
        System.out.println(department.getDepartmentName());
        return department;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getDepartmentByName(String name) throws NoSuchElementFoundException {
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

    public List<Department> getDepartmentsByMinNoOfEmployees(Long employeeCount) {
        List<Department> departments = departmentRepository.findAll();
        departments = departments.stream().filter(d -> d.getEmployees().size() >= employeeCount).collect(Collectors.toList());
        return departments;
    }
}
