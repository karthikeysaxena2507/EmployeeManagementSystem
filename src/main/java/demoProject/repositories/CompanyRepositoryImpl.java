package demoProject.repositories;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.mappers.CompanyMapper;
import demoProject.models.Company;
import demoProject.models.CompanyEntity;
import demoProject.models.NewCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom, demoProject.api.CompanyEntityApi {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    /** @Lazy => Helps to break cyclic bean dependency (loads only an instance of the bean from IOC, not the whole bean **/
    @Autowired
    public CompanyRepositoryImpl(@Lazy CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public List<Company> findByMinNoOfEmployees(Long employeeCount) {
        List<Company> companies = companyRepository.findAll();
        companies = companies.stream().filter(c -> c.getEmployeeCount() >= employeeCount).collect(Collectors.toList());
        return companies;
    }

    @Override
    public CompanyEntity addCompany(NewCompany newCompany) {
        Company company = new Company(newCompany.getCompanyName(), newCompany.getEmployeeCount());
        Company savedCompany = companyRepository.save(company);
        return companyMapper.toCompanyEntity(savedCompany);
    }

    @Override
    public void deleteCompanyById(Long companyId) {
        if(companyRepository.existsById(companyId)) {
            companyRepository.deleteById(companyId);
        }
        else throw new NoSuchElementFoundException();
    }

    @Override
    public List<CompanyEntity> getAllCompanies() {
        List<CompanyEntity> companies = new ArrayList<>();
        List<Company> savedCompanies = companyRepository.findAll();
        for(Company company: savedCompanies) {
            companies.add(companyMapper.toCompanyEntity(company));
        }
        return companies;
    }

    @Override
    public CompanyEntity getCompanyById(Long companyId) throws NoSuchElementFoundException {
        Company company = companyRepository.findById(companyId).orElseThrow(NoSuchElementFoundException::new);
        return companyMapper.toCompanyEntity(company);
    }

    @Override
    public List<CompanyEntity> getCompanyByMinNoEmployees(Long employeeCount) {
        List<CompanyEntity> companies = new ArrayList<>();
        List<Company> savedCompanies = companyRepository.findByMinNoOfEmployees(employeeCount);
        for(Company company: savedCompanies) {
            companies.add(companyMapper.toCompanyEntity(company));
        }
        return companies;
    }

    @Override
    public List<CompanyEntity> getCompanyByName(String companyName) {
        List<CompanyEntity> companies = new ArrayList<>();
        List<Company> savedCompanies = companyRepository.findByCompanyName(companyName);
        for(Company company: savedCompanies) {
            companies.add(companyMapper.toCompanyEntity(company));
        }
        return companies;
    }

    @Override
    public CompanyEntity updateCompany(CompanyEntity companyEntity) {
        if(companyRepository.existsById(companyEntity.getCompanyId())) {
            Company updatedCompany = companyRepository.save(companyMapper.toCompany(companyEntity));
            return companyMapper.toCompanyEntity(updatedCompany);
        }
        else throw new NoSuchElementFoundException();
    }
}
