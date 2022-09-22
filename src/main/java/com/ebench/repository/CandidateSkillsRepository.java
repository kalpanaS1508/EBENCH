package com.ebench.repository;

import com.ebench.dto.CandidateSkillsResDto;
import com.ebench.entity.CandidateSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateSkillsRepository extends JpaRepository<CandidateSkills , Long> {

    @Query("select new com.ebench.dto.CandidateSkillsResDto(cs.candidateSkillsId , cs.experience , cs.rating , cs.skills , " +
            " cs.candidateId ) from CandidateSkills cs left join Candidate c on c.candidateId = cs.candidateId where cs.candidateId = ?1 ")
    List<CandidateSkillsResDto> findByCandidateId(Long candidateId);
}
