package com.ebench.service;
import com.ebench.dto.jobResponseDto.AppliedJobResDto;
import com.ebench.entity.AppliedJobs;
import com.ebench.entity.Candidate;
import com.ebench.entity.Jobs;
import com.ebench.exception.BadReqException;
import com.ebench.repository.AppliedJobsRepository;
import com.ebench.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppliedJobsService {

    @Autowired
    AppliedJobsRepository appliedJobsRepository;

    public AppliedJobs create(AppliedJobs appliedJobs) {

//        if(candidateAlreadyExist(appliedJobs.getCandidateId())){
//            throw new BadReqException("You have already applied on this job");
//        }

            AppliedJobs appliedJobs1 = new AppliedJobs();
            appliedJobs1.setJobId(appliedJobs.getJobId());
            appliedJobs1.setCandidateId(appliedJobs.getCandidateId());
            appliedJobs1.setJobAcceptanceStatus(appliedJobs.getJobAcceptanceStatus());


            AppliedJobs jobs = appliedJobsRepository.save(appliedJobs1);

            return jobs;

    }
    
    public List<AppliedJobResDto> getCandidateListAppliedJobs(Long jobId){
        List<AppliedJobResDto> appliedJob = appliedJobsRepository.findByJobId(jobId);
        return appliedJob;
    }


    public Boolean candidateAlreadyExist(Long candidateId) {
        boolean candidate = appliedJobsRepository.existsById(candidateId);
        System.out.println("--------------------- " + candidate);
        return true;
    }
}
