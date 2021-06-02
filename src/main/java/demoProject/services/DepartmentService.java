package demoProject.services;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import demoProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getDepartmentById(Long departmentId) throws NoSuchElementFoundException {
        return departmentRepository.findById(departmentId).orElseThrow(NoSuchElementFoundException::new);
    }


    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) throws NoSuchElementFoundException {
          return departmentRepository.save(department);
//        if(departmentRepository.existsById(department.getDepartmentId())) {
//            return departmentRepository.save(department);
//        }
//        else throw new NoSuchElementFoundException();
    }

    public void deleteDepartment(Long departmentId) throws NoSuchElementFoundException {
        departmentRepository.deleteById(departmentId);
//        if(departmentRepository.existsById(departmentId)) {
//            departmentRepository.deleteById(departmentId);
//        }
//        else throw new NoSuchElementFoundException();
    }
}
