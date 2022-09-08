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

  @Query("select new com.ebench.dto.jobResponseDto.JobResponseDto(j.jobId ,count(aj.candidateId) as numberOfCandidates ," +
          "count(aj.resumeReceived) as numberOfResume ,j.jobTitle ,j.requiredSkills) from Jobs j " +
          "right join AppliedJobs aj on aj.jobId = j.jobId " +
          "left join Candidate c on c.candidateId = aj.candidateId where j.jobStatus = ?1 " +
          "group by aj.jobId ")
  List<JobResponseDto> findByStatus(boolean jobStatus);


 List<Jobs> findByJobDescriptionAndJobLocationAndJobFilter(String jobDescription, String jobLocation, JobFilter jobFilter);


 @Query(value = "select * from jobs j where j.client_name = ifnull(?1,j.client_name) \n" +
         "  and j.job_location = ifnull(?2,j.job_location) and j.job_description = ifnull(?3 ,j.job_title)\n" +
         "  and j.skills = ifnull(?4 ,j.skills) and j.shift_time = ifnull(?5,j.shift_time) ", nativeQuery = true)

 List<Jobs> findByJobSearches(String clientName, String jobLocation, String jobTitle, String skills, String shiftTime);
 }

