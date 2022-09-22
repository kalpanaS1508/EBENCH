package com.ebench.repository;

import com.ebench.dto.InterviewResDto;
import com.ebench.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    @Query("select new com.ebench.dto.InterviewResDto (i.interviewId , i.interviewDate , i.hiringStatus , i.rating , " +
            " i.candidateName , c.candidateId) from Interview i " +
            " left join Candidate c on i.candidateId = c.candidateId " +
            " where i.hiringStatus = ?1 ")
    List<InterviewResDto> findByHiringStatus(String hiringStatus);

    @Query("select new com.ebench.dto.InterviewResDto(i.interviewId , i.interviewDate , i.hiringStatus , " +
            " i.rating , i.candidateName ,i.jobPosition , i.vendorId , i.interviewSlot , i.reschedule)" +
            " from Interview i where i.interviewDate <= :interviewDate")
    List<InterviewResDto> findByInterviewDate(@Param("interviewDate") String interviewDate);

    @Query("select new com.ebench.dto.InterviewResDto(i.interviewId , i.interviewDate , i.hiringStatus , " +
            " i.rating , i.candidateName ,i.jobPosition , i.vendorId , i.interviewSlot , i.reschedule)" +
            " from Interview i where i.interviewDate > :interviewDate")
    List<InterviewResDto>findByIntDate(@Param("interviewDate") String interviewDate);

    Interview findByInterviewIdAndCandidateId(Long interviewId, Long candidateId);

    Interview findByInterviewIdAndTeamMemberId(Long interviewId, Long teamMemberId);

    Optional<Interview> findByInterviewId(Long interviewId);
}