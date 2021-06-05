package demoProject.repositories;

import demoProject.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {
    List<Company> findByCompanyName(String companyName);
}
