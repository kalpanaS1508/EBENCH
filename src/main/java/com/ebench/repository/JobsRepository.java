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
         "j.jobTitle ,j.requiredSkills , j.vendorId ,j.requiredExperience ,j.jobLocation) from Jobs j " +
         "left join AppliedJobs aj on aj.jobId = j.jobId " +
         "left join Candidate c on c.candidateId = aj.candidateId where j.jobStatus = ?1 and j.vendorId = ?2 " +
         "group by aj.jobId ")
 List<JobResponseDto> findByJobStatus(boolean jobStatus, Long vendorId);

 @Query("select new com.ebench.dto.jobResponseDto.JobResponseDto(j.jobId ,j.vendorId,j.jobTitle, j.jobLocation," +
         " j.requiredSkills, j.totalCandidate ,j.requiredExperience) from Jobs j where j.vendorId = ?1")
 List<JobResponseDto> findByVendorId(Long vendorId);

 List<Jobs> findByJobDescriptionAndJobLocationAndJobFilter(String jobDescription, String jobLocation, JobFilter jobFilter);
}


// @Query(value = "select * from jobs j where j.client_name = ifnull(?1,j.client_name) \n" +
//         "  and j.job_location = ifnull(?2,j.job_location) and j.job_description = ifnull(?3 ,j.job_title)\n" +
//         "  and j.skills = ifnull(?4 ,j.skills) and j.shift_time = ifnull(?5,j.shift_time) ", nativeQuery = true)
//
// List<Jobs> findByJobSearches(String clientName, String jobLocation, String jobTitle, String skills, String shiftTime);
// }

