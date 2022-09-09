package com.ebench.service;
import com.ebench.Apimessage.ApiMessage;
import com.ebench.Enums.RequestType;
import com.ebench.dto.jobResponseDto.AppliedJobResDto;
import com.ebench.entity.AppliedJobs;
import com.ebench.entity.Candidate;
import com.ebench.entity.Jobs;
import com.ebench.entity.Notification;
import com.ebench.exception.BadReqException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.AppliedJobsRepository;
import com.ebench.repository.JobsRepository;
import com.ebench.repository.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppliedJobsService {

    @Autowired
    AppliedJobsRepository appliedJobsRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    NotificationRepository notificationRepository;

    public AppliedJobs create(AppliedJobs appliedJobs) {

//        if(candidateAlreadyExist(appliedJobs.getCandidateId())){
//            throw new BadReqException("You have already applied on this job");
//        }

            AppliedJobs appliedJobs1 = new AppliedJobs();
            Jobs job = new Jobs();
            appliedJobs1.setJobId(appliedJobs.getJobId());
            appliedJobs1.setCandidateId(appliedJobs.getCandidateId());
            appliedJobs1.setResumeReceived(appliedJobs.getResumeReceived());

            Integer previousTotalCandidateCount = job.getTotalCandidate();
            Integer totalCandidateCount = previousTotalCandidateCount +1;
            job.setTotalCandidate(totalCandidateCount);
//            jobsRepository.save();


        AppliedJobs jobs = appliedJobsRepository.save(appliedJobs1);


        return jobs;


    }

    public List<AppliedJobResDto> getCandidateListAppliedJobs(Long jobId) {
        List<AppliedJobResDto> appliedJobResDto = appliedJobsRepository.findByJobId(jobId);
        return appliedJobResDto;
    }

    public Boolean candidateAlreadyExist(Long candidateId) {
        boolean candidate = appliedJobsRepository.existsById(candidateId);
        System.out.println("--------------------- " + candidate);
        return true;
    }


    //___________________Create Notification_______________________________//

    public Notification createNotification(Notification notification) {
        try {
            Notification notification1 = notificationRepository.save(notification);
            return notification1;
        } catch (Exception e) {
            throw new BadReqException(ApiMessage.NOTIFICATION_NOT_CREATED);
        }
    }


    //__________________Get Notification________________________________//

    public List<Notification> getCandidateOnRequestBasis(String requestType)
    {

      List<Notification>notifications= notificationRepository.findByRequestType(RequestType.valueOf(requestType));

        return  notifications;

    }




    //_______________update status if accept __________________________//

       public AppliedJobs updateStatus(Long appliedJobsId ) {

        Optional<AppliedJobs> appliedJobsOptional = appliedJobsRepository.findById(appliedJobsId);

        System.out.println(appliedJobsOptional);

        AppliedJobs appliedJobs = null;

        if (appliedJobsOptional.isPresent()) {
            appliedJobs = appliedJobsOptional.get();
        }
        else {
            throw new UserNotFoundException("Candidate Not Found");
        }
        appliedJobs.setJobAcceptanceStatus(Boolean.TRUE);
        appliedJobsRepository.save(appliedJobs);
        return appliedJobs;

         }

    public List<Notification> getAllNotification() {
      List<Notification>notifications = notificationRepository.findAll();
      return notifications;
    }



}
