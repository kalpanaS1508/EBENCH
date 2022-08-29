package com.ebench.repository;

import com.ebench.dto.jobResponseDto.JobResponseDto;
import com.ebench.Enums.JobFilter;
import com.ebench.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
 public interface JobsRepository extends JpaRepository<Jobs , Long> {

  @Query("select new com.ebench.dto.jobResponseDto.JobResponseDto(j.jobTitle, j.jobStatus, j.postedDate ," +
          " j.requiredSkills, j.requiredExperience , j.jobLocation , j.companyName) from Jobs j where jobStatus = ?1")
  List<JobResponseDto> findByStatus(boolean jobStatus);


  @Query("select new com.ebench.dto.jobResponseDto.JobResponseDto( j.jobId ,j.jobTitle ,j.postedDate ,j.jobStatus , j.candidateSelection) from Jobs j where j.candidateSelection = ifnull(?1,j.candidateSelection) and j.jobTitle =ifnull(?2, j.jobTitle) and j.postedDate =ifnull(?3,j.postedDate)")
  List<JobResponseDto> postedJobs(boolean candidateSelection ,String jobTitle ,String postedDate);


 List<Jobs> findByJobTitleAndJobLocationAndJobFilter(String jobTitle, String jobLocation, JobFilter jobFilter);


 @Query(value = "select * from jobs j where j.client_name = ifnull(?1,j.client_name) \n" +
         "  and j.job_location = ifnull(?2,j.job_location) and j.job_title = ifnull(?3 ,j.job_title)\n" +
         "  and j.skills = ifnull(?4 ,j.skills) and j.shift_time = ifnull(?5,j.shift_time) ", nativeQuery = true)
 List<Jobs> findByJobSearches(String clientName, String jobLocation, String jobTitle, String skills, String shiftTime);
 }

