package com.ebench.repository;

import com.ebench.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor , Long> {
}
