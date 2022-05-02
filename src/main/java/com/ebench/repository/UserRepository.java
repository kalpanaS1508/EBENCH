package com.ebench.repository;

import com.ebench.dto.loginDto.LoginResponseDto;
import com.ebench.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select new com.ebench.entity.User(u.id,u.name,u.keyExperience,u.skills,u.address,u.skypeId,u.whatsapp,u.country," +
            "u.state,u.city,u.hobbies,u.email,u.interest,u.mobile,u.availableForWork,u.password,u.profileImageUrl,u.userType) " +
            " from User u where ((?1 = '' or u.skills =?1) and" +
            "(?2 = '' or u.keyExperience = ?2) and" +
            "(?3 = '' or u.state = ?3) )and u.userType = 'CANDIDATE' ")
    List<User> findBySkillsandExp();


    @Query("Select new com.ebench.entity.User(u.id,u.name,u.keyExperience,u.skills) from User u where availableForWork = ?1 and userType = 'CANDIDATE'")
    List<User> findByNameandKey(boolean availableForWork, Sort columnName);


    @Query("Select new com.ebench.entity.User(u.id,u.name,u.keyExperience,u.skills,u.address,u.skypeId,u.whatsapp,u.country," +
            "u.state,u.city,u.hobbies,u.email,u.interest,u.mobile,u.availableForWork,u.password,u.profileImageUrl,u.userType) " +
            " from User u where u.email = :email and u.password = :password")
    User findByEmailandPassword(@Param("email") String email, @Param("password") String password);

    @Query("select new com.ebench.dto.loginDto.LoginResponseDto(u.id,u.name,u.keyExperience,u.skills,u.address,u.skypeId,u.whatsapp,u.country," +
            "u.state,u.city,u.hobbies,u.email,u.interest,u.mobile,u.availableForWork,u.password,u.profileImageUrl,u.userType) " +
            " from User u where u.email =?1 ")
    LoginResponseDto findByEmail(String email);
}