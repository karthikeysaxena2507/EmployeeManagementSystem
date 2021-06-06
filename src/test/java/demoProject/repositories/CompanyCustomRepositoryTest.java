package demoProject.repositories;

import demoProject.mappers.CompanyMapper;
import demoProject.models.Company;
import demoProject.models.CompanyEntity;
import demoProject.models.NewCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CompanyCustomRepositoryTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    private CompanyRepositoryImpl companyRepositoryImpl;
    private Company company;
    private CompanyEntity companyEntity;
    private NewCompany newCompany;

    @BeforeEach
    public void setup() {
        companyRepositoryImpl = new CompanyRepositoryImpl(companyRepository, companyMapper);
        company = new Company(1L, "A", 5L);
        companyEntity = new CompanyEntity(1L, "A", 5L);
        newCompany = new NewCompany("A", 5L);
    }

    @Test
    @DisplayName("test get company by id")
    public void testGetCompanyById() {
        Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        Mockito.when(companyMapper.toCompanyEntity(company)).thenReturn(companyEntity);
        CompanyEntity actualCompany = companyRepositoryImpl.getCompanyById(1L);
        Assertions.assertEquals(company.getCompanyId(), actualCompany.getCompanyId());
    }

    @Test
    @DisplayName("test get All companies")
    public void testGetAllCompanies() {
        List<Company> savedCompanies = new ArrayList<>();
        savedCompanies.add(company);
        Mockito.when(companyRepository.findAll()).thenReturn(savedCompanies);
        Mockito.when(companyMapper.toCompanyEntity(company)).thenReturn(companyEntity);
        List<CompanyEntity> actualCompanies = companyRepositoryImpl.getAllCompanies();
        Assertions.assertEquals(actualCompanies.size(), savedCompanies.size());
    }

    @Test
    @DisplayName("test add company")
    public void testAddCompany() {
        companyRepositoryImpl.addCompany(newCompany);
        Mockito.verify(companyRepository, Mockito.times(1)).save(ArgumentMatchers.any(Company.class));
    }

    @Test
    @DisplayName("test get companies by name")
    public void testGetCompanyByName() {
        List<Company> savedCompanies = new ArrayList<>();
        savedCompanies.add(company);
        Mockito.when(companyRepository.findByCompanyName("A")).thenReturn(savedCompanies);
        Mockito.when(companyMapper.toCompanyEntity(company)).thenReturn(companyEntity);
        List<CompanyEntity> actualCompanies = companyRepositoryImpl.getCompanyByName("A");
        Assertions.assertEquals(actualCompanies.size(), savedCompanies.size());
    }

    @Test
    @DisplayName("test get companies by min no. of employees")
    public void testGetCompanyByMinNoOfEmployees() {
        List<Company> savedCompanies = new ArrayList<>();
        savedCompanies.add(company);
        Mockito.when(companyRepository.findByMinNoOfEmployees(1L)).thenReturn(savedCompanies);
        Mockito.when(companyMapper.toCompanyEntity(company)).thenReturn(companyEntity);
        List<CompanyEntity> actualCompanies = companyRepositoryImpl.getCompanyByMinNoEmployees(1L);
        Assertions.assertEquals(actualCompanies.size(), savedCompanies.size());
    }

    @Test
    @DisplayName("test update company")
    public void testUpdateCompany() {
        CompanyEntity newCompanyEntity = new CompanyEntity(1L, "A", 8L);
        Company updatedCompany = new Company(1L, "A", 8L);
        Mockito.when(companyRepository.existsById(1L)).thenReturn(true);
        Mockito.when(companyMapper.toCompanyEntity(updatedCompany)).thenReturn(newCompanyEntity);
        Mockito.when(companyMapper.toCompany(newCompanyEntity)).thenReturn(updatedCompany);
        Mockito.when(companyRepository.save(updatedCompany)).thenReturn(updatedCompany);
        CompanyEntity updatedCompanyEntity = companyRepositoryImpl.updateCompany(newCompanyEntity);
        Assertions.assertEquals(newCompanyEntity, updatedCompanyEntity);
    }

    @Test
    @DisplayName("test delete company")
    public void testDeleteCompany() {
        companyRepository.deleteById(1L);
        Mockito.verify(companyRepository, Mockito.times(1)).deleteById(1L);
    }

}
