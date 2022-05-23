package com.ebench.repository;

import com.ebench.entity.Candidate;
import com.ebench.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor , Long> {

    Optional<Vendor> findVendorByEmail(String email);

}
