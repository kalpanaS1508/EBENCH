package com.ebench.repository;

import com.ebench.entity.Candidate;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    Optional<Candidate> findUserByEmail(String email);

    @Query(value = "select * from candidate c \n" +
            "where c.key_experience = ifnull(?1 ,c.key_experience) and c.skills = ifnull(?2 ,c.skills) and c.city =ifnull(?3 ,c.city) " +
            "and c.mobile =ifnull(?4 ,c.mobile) " ,nativeQuery = true)
    List<Candidate> findBySkillAndExperience(String keyExperience, String skills, String city, String mobile);

}


//"Select new com.ebench.entity.Candidate(c.id,c.firstName,c.lastName,c.email,c.address,c.keyExperience,c.skills,c.whatsapp,c.city)" +
//        " from Candidate c where c.keyExperience =?1 and c.skills LIKE concat('%',?2,'%') and c.firstName =?3 and c.city =?4 and c.whatsapp =?5")