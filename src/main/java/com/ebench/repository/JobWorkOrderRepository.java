package com.ebench.repository;

import com.ebench.entity.JobWorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobWorkOrderRepository extends JpaRepository<JobWorkOrder , Long> {



}
