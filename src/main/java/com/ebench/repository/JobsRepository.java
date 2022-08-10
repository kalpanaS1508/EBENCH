package com.ebench.repository;

import com.ebench.dto.jobResponseDto.JobResponseDto;
import com.ebench.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
 public interface JobsRepository extends JpaRepository<Jobs , Long> {

  @Query(value = "select j.job_id ,j.vendor_id ,j.job_title ,j.posted_date ,j.job_status from jobs j where j.vendor_id=?1 " , nativeQuery = true)
  List<Jobs> findByJobStatus(Long vendorId);

  @Query("select new com.ebench.dto.jobResponseDto.JobResponseDto(j.jobId, j.jobTitle, j.jobStatus, j.postedDate)from Jobs j where jobStatus = ?1")
  List<JobResponseDto> findByStatus(boolean jobStatus);

  @Query("select new com.ebench.dto.jobResponseDto.JobResponseDto( j.jobId ,j.jobTitle ,j.postedDate ,j.jobStatus , j.candidateSelection) from Jobs j where j.candidateSelection = ifnull(?1 ,j.candidateSelection) and j.jobTitle =ifnull(?2 , j.jobTitle) and j.postedDate =ifnull(?3 ,j.postedDate)")
  List<Jobs> postedJobs(boolean candidateSelection ,String jobTitle ,String postedDate);


 }

