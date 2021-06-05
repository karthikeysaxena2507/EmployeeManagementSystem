package demoProject.mappers;

import demoProject.models.Company;
import demoProject.models.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyMapper() {

    }

    public Company toCompany(CompanyEntity companyEntity) {
        return new Company(companyEntity.getCompanyId(), companyEntity.getCompanyName(), companyEntity.getEmployeeCount());
    }

    public CompanyEntity toCompanyEntity(Company company) {
        return new CompanyEntity(company.getCompanyId(), company.getCompanyName(), company.getEmployeeCount());
    }



}
