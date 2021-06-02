package demoProject.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;
import org.eclipse.persistence.annotations.PrivateOwned;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id", unique = true, nullable = false)
    private Long departmentId;

    @Column(name = "department_name", nullable = false)
    @NotNull
    private String departmentName;

    /**
     * targetEntity => specifies the target(child) entity
     * mappedBy => used to define the child of the parent-child relationship
     * cascade => Allows to apply Operations on related entities (ALL allows all operations)
     * fetch => specifies the type of fetching when fetching parent data
     * -- LAZY: fetch when needed
     * -- EAGER: fetch immediately
     * JsonManagedReference => used to prevent infinite recursion while serializing
     * BatchFetch => Allows to read multiple objects via single query
     * PrivateOwned => If the reference of the child is deleted from parent, Eclipse
     *                 Link will delete the child from the child table as well.
     *                 It prevents the need to explicitly delete items from child table.
     */
    @OneToMany(targetEntity = Employee.class, mappedBy = "department", cascade = ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @BatchFetch(BatchFetchType.IN)
    @PrivateOwned
    List<Employee> employees;

    protected Department() {

    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(String departmentName, List<Employee> employees) {
        this.departmentName = departmentName;
        this.employees = employees;
    }

    public Department(Long departmentId, String departmentName, List<Employee> employees) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employees = employees;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
