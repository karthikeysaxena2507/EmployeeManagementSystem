package demoProject.repositories;

import demoProject.mappers.CompanyMapper;
import demoProject.models.Company;
import demoProject.models.CompanyEntity;
import demoProject.models.NewCompany;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Optional;

@SpringBootTest
public class CompanyCustomRepositoryTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    private CompanyRepositoryImpl companyRepositoryImpl;
    private Company company;
    private CompanyEntity companyEntity;
    private NewCompany newCompany;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
        companyRepositoryImpl = new CompanyRepositoryImpl(companyRepository, companyMapper);
        company = new Company(1L, "A", 5L);
        companyEntity = new CompanyEntity(1L, "A", 5L);
        newCompany = new NewCompany("A", 5L);
    }

    @Test
    public void testGetCompanyById() {
        Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        Mockito.when(companyMapper.toCompanyEntity(company)).thenReturn(companyEntity);
        CompanyEntity actualCompany = companyRepositoryImpl.getCompanyById(1L);
        Assert.assertEquals(company.getCompanyId(), actualCompany.getCompanyId());
    }

}
