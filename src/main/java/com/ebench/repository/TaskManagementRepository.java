package com.ebench.repository;

import com.ebench.entity.TaskManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManagementRepository extends JpaRepository<TaskManagement, Long> {

//    @Query("select new com.ebench.entity.TaskManagement( tm.taskId, tm.taskName, tm.addComment, tm.description, tm.acceptance, tm.candidateId, tm.taskOwnerId, tm.taskStatus, tm.startDate, tm.dueDate) from TaskManagement tm where taskId=?1 and UserType = 'VENDOR'" )
//    TaskManagement addTaskManagement(Long userId);
}
