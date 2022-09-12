package com.ebench.repository;

import com.ebench.Enums.RequestType;
import com.ebench.entity.Candidate;
import com.ebench.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query(value = "SELECT n.notification_id, n.request_type ,n.candidate_id , n.vendor_id,n.applied_job_id ,n.status ,n.job_id  from notification n\n" +
            "left join candidate c on c.candidate_id = n.candidate_id \n" +
            "where n.request_type =\"OFFER\" ",nativeQuery = true)
    List<Candidate> findByCandidateId(Long candidateId);

    List<Notification> findByRequestType(RequestType requestType);
}
