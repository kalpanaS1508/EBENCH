package com.ebench.repository;

import com.ebench.dto.CandidateReqDto;
import com.ebench.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {


    Optional<Candidate> findUserByEmail(String email);

    @Query(value = "select * from candidate c where email=?1 and password=?2",nativeQuery = true)
    Candidate findByEmailAndPassword(String email , String password);
}
