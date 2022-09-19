package com.ebench.repository;

import com.ebench.Enums.RequestType;
import com.ebench.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query(value = "select * from notification n where n.candidate_id = ?1 ",nativeQuery = true)
    List<Notification> findByCandidateId(Long candidateId);

    List<Notification> findByRequestType(RequestType requestType);


}
