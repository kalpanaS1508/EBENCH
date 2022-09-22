package com.ebench.service;

import com.ebench.entity.Company;
import com.ebench.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company addCompany(Company company) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(company.getCompanyEmail())
                .matches();
        System.out.println((emailValidation));


        Company company1 = companyRepository.save(company);
        return company1;
    }

    public Company updateCompany(Company company){

        Optional<Company> companyId = companyRepository.findById(company.getCompanyId());

        Company company1 = null;

        if(companyId.isPresent()) {
            company1 = companyId.get();

            if (company.getCompanyId() != null) {
                company1.setCompanyId(company.getCompanyId());
            }

            if (company.getCandidateId() != null) {
                company1.setCandidateId(company.getCandidateId());
            }

            if (company.getVendorId() != null) {
                company1.setVendorId(company.getVendorId());
            }

            if (company.getCompanyName() != null) {
                company1.setCompanyName(company.getCompanyName());
            }

            if (company.getCompanyAddress() != null) {
                company1.setCompanyAddress(company.getCompanyAddress());
            }

            if (company.getCompanyPhoneNumber() != null) {
                company1.setCompanyPhoneNumber(company.getCompanyPhoneNumber());
            }

            if (company.getAboutCompany() != null) {
                company1.setAboutCompany(company.getAboutCompany());
            }

            if (company.getCompanyLocation() != null) {
                company1.setCompanyLocation(company.getCompanyLocation());
            }

            Company company2 = companyRepository.save(company1);
            return company2;
        }
        return company1;
    }
}
