package com.ebench.repository;

import com.ebench.entity.ChangeTaskStatus;
import com.ebench.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query(value = "select t.project_name from taskmanagement t where candidate_id =1 ",nativeQuery = true)
    public Set<String> findByCandidateId(Long id);

    @Query("Select t from Task t where t.id=:id AND t.projectName=:projectName AND t.changeTaskStatus=:changeTaskStatus")
    public List<Task> findAllTasks(Long id, String projectName, ChangeTaskStatus changeTaskStatus);

    Task getBytaskManagementId(Long taskManagementId);
}
