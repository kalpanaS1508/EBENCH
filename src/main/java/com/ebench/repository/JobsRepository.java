package com.ebench.repository;

import com.ebench.entity.JobFilter;
import com.ebench.entity.Jobs;
import com.ebench.responses.JobResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
 public interface JobsRepository extends JpaRepository<Jobs , Long> {

  @Query(value = "select j.job_id ,j.vendor_id ,j.job_title ,j.posted_date ,j.job_status from jobs j where j.vendor_id=?1 " , nativeQuery = true)
  List<Jobs> findByJobStatus(Long vendorId);

//  @Query(value = "select  * from jobs j \n" +
//          "where j.job_title =?1 and j.job_location=?2 ",nativeQuery = true)
       List<Jobs> findByJobTitleAndJobLocationAndJobFilter(String jobTitle, String jobLocation, JobFilter jobFilter);


}

