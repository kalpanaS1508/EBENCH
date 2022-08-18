package com.ebench.service;
import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.jobResponseDto.AppliedJobResDto;
import com.ebench.entity.AppliedJobs;
import com.ebench.exception.BadReqException;
import com.ebench.repository.AppliedJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppliedJobsService {

    @Autowired
    AppliedJobsRepository appliedJobsRepository;

    public AppliedJobs create(AppliedJobs appliedJobs){
        AppliedJobs jobs = new AppliedJobs();
        jobs.setJobId(appliedJobs.getJobId());
        jobs.setCandidateId(appliedJobs.getCandidateId());
        jobs.setJobTitle(appliedJobs.getJobTitle());
        jobs.setResumeReceived(appliedJobs.getResumeReceived());
        AppliedJobs jobs1 = appliedJobsRepository.save(jobs);
        return jobs1;
    }

    public AppliedJobResDto getCountResume(Long jobId) {
        AppliedJobResDto appliedJobResDto = appliedJobsRepository.countByJobId(jobId);
        return appliedJobResDto;


    }
}
