package com.ebench.repository;

import com.ebench.dto.jobResponseDto.AppliedJobResDto;
import com.ebench.entity.AppliedJobs;
import com.ebench.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppliedJobsRepository extends JpaRepository<AppliedJobs, Long> {

    @Query("select new com.ebench.dto.jobResponseDto.AppliedJobResDto(aj.candidateId ,c.firstName , j.jobTitle , " +
            " c.skills ,aj.resumeReceived, aj.jobId)  from AppliedJobs aj " +
            " left join Jobs j on j.jobId = aj.jobId " +
            " left join Candidate c on c.candidateId = aj.candidateId" +
            " where aj.jobId = ?1 "  )
    List<AppliedJobResDto> findByJobId(Long jobId);

//   Optional<AppliedJobs> findByCandidateId(Long candidateId);
}
