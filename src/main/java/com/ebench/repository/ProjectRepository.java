package com.ebench.repository;

import com.ebench.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query(value = " ",nativeQuery = true)
   public Set<String> findByCandidateId(Long id);
}
