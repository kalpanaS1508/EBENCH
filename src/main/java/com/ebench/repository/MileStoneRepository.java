package com.ebench.repository;

import com.ebench.dto.jobResponseDto.MileStoneResDto;
import com.ebench.entity.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MileStoneRepository extends JpaRepository<MileStone , Long> {

    @Query("select new com.ebench.dto.jobResponseDto.MileStoneResDto(COUNT(sprintName) as TotalSprint ,milestoneEndDate " +
            " ,milestoneStartDate ,mileStoneName ) " +
            " from MileStone where mileStoneName =?1 ")
    MileStoneResDto findByCandidateId(String mileStoneName);

}