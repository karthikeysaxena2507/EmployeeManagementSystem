package demoProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    @Column(name = "name", nullable = false)
    @NotNull
    private String employeeName;

    /**
     * @JoinColumn => Used to form a bidirectional association via foreign key
     * @JsonBackReference => It will be omitted from serialization by jackson to prevent infinite recursion
     */
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference
    @BatchFetch(BatchFetchType.IN)
    private Department department;

    protected Employee() {

    }

    public Employee(Long employeeId, String employeeName, Department department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
    }

    public Employee(String employeeName, Department department) {
        this.employeeName = employeeName;
        this.department = department;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
