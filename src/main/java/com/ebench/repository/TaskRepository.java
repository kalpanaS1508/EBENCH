package com.ebench.repository;

import com.ebench.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(value = "select p.project_name from project p where candidate_id =?1",nativeQuery = true)
    public Set<String> findByCandidateId(Long candidateId);

    @Query(value="select t.task_name from taskmanagement t join\n" +
            "candidate c on c.id =t.candidate_id where candidate_id =?1",nativeQuery = true)
    public List<String>findAllTasks(Long candidateId);

     @Query(value="select * from taskmanagement t\n" +
             "join candidate c on c.id =t.candidate_id where t.candidate_id =?1",nativeQuery = true)
       public List<Task>findTaskHistory(Long candidateId);

    @Query(value = "select t.task_name ,t.change_task_status,t.task_description from taskmanagement t\n" +
            "join candidate c on c.id = t.candidate_id where candidate_id =?1 and change_task_status =\"PENDING\" ",nativeQuery = true)
        List<String> findPendingTasks(Long candidateId);

     @Query(value = "select t.task_due_date ,t.task_start_date ,t.no_of_delayed_date ,c.first_name ,c2.client_name from taskmanagement t \n" +
             "join client c2 on c2.id =t.client_id \n" +
             "join candidate c on c.id = t.candidate_id where t.candidate_id =?1",nativeQuery = true)
          public Task findByTaskOwnerId(Long candidateId);

//     @Query(value = "select t.task_description ,t.task_name from taskmanagement t where t.project_name =:projectName",nativeQuery = true)
    /*@Query(value = "select t.task_name ,t.change_task_status,t.task_description from taskmanagement t\n" +
        " where project_name =?1 and change_task_status =\"PENDING\" ",nativeQuery = true)
    public List<Task> findTaskByProjectName(String projectName);*/


     @Query("Select t from Task t where t.projectName=?1 AND t.changeTaskStatus='PENDING'")
     public List<Task> findTaskProjectName(String projName);
}
