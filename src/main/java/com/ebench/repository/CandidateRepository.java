package com.ebench.repository;

import com.ebench.dto.CandidateResDto;
import com.ebench.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {


    Optional<Candidate> findUserByEmail(String email);

           @Query(value = "select * from candidate c where email=?1 and password=?2",nativeQuery = true)
           Candidate findByEmailAndPassword(String email , String password);

            @Query(value = "select * from candidate c \n" +
            "where c.key_experience = ifnull(?1 ,c.key_experience) and c.skills = ifnull(?2 ,c.skills) and c.city =ifnull(?3 ,c.city) " +
            "and c.mobile =ifnull(?4 ,c.mobile) " ,nativeQuery = true)
         List<Candidate> findBySkillAndExperience(String keyExperience, String skills, String city, String mobile);

            @Query("Select c from Candidate c where c.email=:email")
            Candidate findByEmail(String email);

            @Query("Select c from Candidate c where c.password=:password")
            Candidate findByPassword(String password);

    @Query(value = "select c.email from candidate c where id = ?1",nativeQuery = true)
    Optional<Candidate> findByCandidateId(Long id);

    @Query("select new com.ebench.dto.CandidateResDto (email,keyExperience,city,mobile) from Candidate where candidateId= ?1")
    CandidateResDto findByCandidateIds(Long candidateId);

    @Query("select new com.ebench.dto.CandidateResDto (linkedIn,skypeId,twitterId) from Candidate where candidateId= ?1")
    CandidateResDto findByCandidateSocialIds(Long candidateId);
}


