package com.ebench.repository;

import com.ebench.dto.jobResponseDto.AppliedJobResDto;
import com.ebench.entity.AppliedJobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface AppliedJobsRepository extends JpaRepository<AppliedJobs, Long> {

    @Query("select new com.ebench.dto.jobResponseDto.AppliedJobResDto " +
            "(COUNT(resumeReceived) as numberOfResume," +
            "COUNT(candidateId) as numberOfCandidate," +
            "jobTitle, jobId, resumeReceived) from AppliedJobs where jobId = ?1")
      public AppliedJobResDto countByJobId(Long jobId);

}
