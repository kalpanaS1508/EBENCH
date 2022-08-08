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

 @Query(value = "select j.job_id ,j.vendor_id ,j.job_title ,j.posted_date ,j.job_status from jobs j where j.vendor_id=?1 ", nativeQuery = true)
 List<Jobs> findByJobStatus(Long vendorId);


 List<Jobs> findByJobTitleAndJobLocationAndJobFilter(String jobTitle, String jobLocation, JobFilter jobFilter);


 @Query(value = "select * from jobs j where j.client_name = ifnull(?1,j.client_name) \n" +
         "  and j.job_location = ifnull(?2,j.job_location) and j.job_title = ifnull(?3 ,j.job_title)\n" +
         "  and j.skills = ifnull(?4 ,j.skills) and j.shift_time = ifnull(?5,j.shift_time) ", nativeQuery = true)
 List<Jobs> findByJobSearches(String clientName, String jobLocation, String jobTitle, String skills, String shiftTime);


     }