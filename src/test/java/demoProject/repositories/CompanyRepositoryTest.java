package demoProject.repositories;

import demoProject.DemoProjectTests;
import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Company;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyRepositoryTest extends DemoProjectTests {

    @Autowired
    private CompanyRepository companyRepository;

    private Company company;

    @BeforeEach
    public void setup() {
        company = new Company(1L, "A", 5L);
        companyRepository.save(company);
    }

    @AfterEach
    public void clear() {
        if(companyRepository.existsById(1L)) {
            companyRepository.deleteById(1L);
        }
    }

    @Test
    @DisplayName("Test save method")
    public void testSaveCompany() {
        Company savedCompany = companyRepository.save(company);
        Assertions.assertEquals(company.getCompanyName() ,savedCompany.getCompanyName());
    }

    @Test
    @DisplayName("Test find By Id method")
    public void testFindById() {
        Company foundCompany = companyRepository.findById(1L).orElseThrow(NoSuchElementFoundException::new);
        Assertions.assertEquals(company.getCompanyName() ,foundCompany.getCompanyName());
    }

    @Test
    @DisplayName("Test find All method")
    public void testFindAll() {
        List<Company> companies = companyRepository.findAll();
        Assertions.assertEquals(1 ,companies.size());
    }

    @Test
    @DisplayName("Test delete By Id")
    public void testDeleteById() {
        companyRepository.deleteById(1L);
        Assertions.assertTrue(companyRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test find company by name")
    public void testFindByName() {
        List<Company> companies = companyRepository.findByCompanyName("A");
        Assertions.assertEquals(1, companies.size());
    }

    @Test
    @DisplayName("Test exists by id")
    public void testExistsById() {
        Assertions.assertTrue(companyRepository.existsById(1L));
        Assertions.assertFalse(companyRepository.existsById(10L));
    }

}
