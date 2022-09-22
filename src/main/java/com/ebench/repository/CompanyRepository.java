package com.ebench.repository;

import com.ebench.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company , Long> {
//
//    Company findCompanyByEmail(String companyEmail);
}
