package demoProject.repositories;

import demoProject.models.Company;

import java.util.List;

public interface CompanyRepositoryCustom {
    List<Company> findByMinNoOfEmployees(Long employeeCount);
}
