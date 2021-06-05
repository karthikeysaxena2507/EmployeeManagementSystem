package demoProject.models;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id", nullable = false, unique = true)
    private Long companyId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "employee_count", nullable = false)
    private Long employeeCount;

    public Company() {

    }

    public Company(Long companyId, String companyName, Long employeeCount) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeeCount = employeeCount;
    }

    public Company(String companyName, Long employeeCount) {
        this.companyName = companyName;
        this.employeeCount = employeeCount;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Long employeeCount) {
        this.employeeCount = employeeCount;
    }
}
