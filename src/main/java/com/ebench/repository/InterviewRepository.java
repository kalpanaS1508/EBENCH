package com.ebench.repository;

import com.ebench.dto.InterviewResDto;
import com.ebench.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    @Query("select new com.ebench.dto.InterviewResDto (i.interviewId , i.interviewDate , i.hiringStatus , i.rating , " +
            " i.candidateName , c.candidateId ) from Interview i join Candidate c on i.candidateId = c.candidateId" +
            " join Jobs j where hiringStatus = ?1 ")
    List<InterviewResDto> findByHiringStatus(String hiringStatus);

    @Query("select new com.ebench.dto.InterviewResDto(i.interviewId , i.interviewDate , i.hiringStatus , " +
            " i.rating , i.candidateName ,i.jobPosition )" +
            " from Interview i where i.interviewDate = ifnull(?1 , i.interviewDate)")
    List<InterviewResDto>findByInterviewDate(Date interviewDate);
}