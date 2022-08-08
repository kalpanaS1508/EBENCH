package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Candidate;
import com.ebench.entity.JobFilter;
import com.ebench.entity.Jobs;
import com.ebench.exception.BadReqException;
import com.ebench.repository.JobsRepository;
import com.ebench.responses.JobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

  @Autowired
  public JobsRepository jobsRepository;

  public Jobs addJobs(Jobs jobs) {

    Jobs jobs1 = new Jobs();
    try {
      jobs1.setCandidateId(jobs.getCandidateId());
      jobs1.setVendorId(jobs.getVendorId());
      jobs1.setCompanyId(jobs.getCompanyId());
      jobs1.setJobTitle(jobs.getJobTitle());
      jobs1.setClientName(jobs.getClientName());
      jobs1.setClientLocation(jobs.getClientLocation());
      jobs1.setJobLocation(jobs.getJobLocation());
      jobs1.setJobStatus(jobs.isJobStatus());
      jobs1.setCompanyName(jobs.getCompanyName());
      jobs1.setExperience(jobs.getExperience());
      jobs1.setSkills(jobs.getSkills());
      jobs1.setTotalJobs(jobs.getTotalJobs());
      jobs1.setTotalCandidate(jobs.getTotalCandidate());
      jobs1.setNoOfPosition(jobs.getNoOfPosition());
      jobs1.setPostedDate(jobs.getPostedDate());
      jobs1.setResumeReceived(jobs.getResumeReceived());
      jobs1.setInterviewMode(jobs.isInterviewMode());
      jobs1.setJobFilter(jobs.getJobFilter());
      jobs1.setJobDescription(jobs.getJobDescription());
      jobs1.setAboutCompany(jobs.getAboutCompany());
      jobs1.setMinimumQualification(jobs.getMinimumQualification());
      jobs1.setPreferredQualification(jobs.getPreferredQualification());
      jobs1.setJobAcceptanceorRejectionStatus(jobs.isJobAcceptanceorRejectionStatus());
      jobs1.setShiftTime(jobs.getShiftTime());
      jobsRepository.save(jobs1);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return jobs;
  }

//  ---------------------------------- JOBS UPDATE--------------------------------------------------------------

  public Jobs updateJobs(Jobs jobs) {
    Optional<Jobs> jobId = jobsRepository.findById(jobs.getJobId());

    if (jobId.isPresent()) {

      Jobs jobs1 = jobId.get();
      try {
        jobs1.setCandidateId(jobs.getCandidateId());
        jobs1.setVendorId(jobs.getVendorId());
        jobs1.setCompanyId(jobs.getCompanyId());
        jobs1.setJobTitle(jobs.getJobTitle());
        jobs1.setClientName(jobs.getClientName());
        jobs1.setClientLocation(jobs.getClientLocation());
        jobs1.setJobLocation(jobs.getJobLocation());
        jobs1.setJobStatus(jobs.isJobStatus());
        jobs1.setCompanyName(jobs.getCompanyName());
        jobs1.setTotalJobs(jobs.getTotalJobs());
        jobs1.setTotalCandidate(jobs.getTotalCandidate());
        jobs1.setNoOfPosition(jobs.getNoOfPosition());
        jobs1.setPostedDate(jobs.getPostedDate());
        jobs1.setResumeReceived(jobs.getResumeReceived());
        jobs1.setInterviewMode(jobs.isInterviewMode());

        jobsRepository.save(jobs);

      } catch (Exception e) {
        throw new BadReqException(ApiMessage.JOB_NOT_PRESENT);
      }
    }
    if (!jobId.isPresent()) {
      throw new BadReqException(ApiMessage.JOB_NOT_PRESENT);
    }
    return jobs;
  }

//------------------------------- JOB DELETE API------------------------------------------------

  public Jobs deleteJob(Long jobId) {
    Optional<Jobs> id = jobsRepository.findById(jobId);
    if (!id.isPresent()) {
      throw new BadReqException(ApiMessage.JOB_NOT_PRESENT);
    } else {
      id.isPresent();
      Jobs jobs = id.get();
      jobsRepository.deleteById(jobs.getJobId());
    }
    return null;
  }

//----------------------------------- GET JOBS ID ---------------------------------------------------------------

  public List<Jobs> getJobStatus(Long vendorId) {
    List<Jobs> status = jobsRepository.findByJobStatus(vendorId);
    return status;
  }


  public Jobs activeDeactiveStatus(Long jobId, boolean jobStatus) {
    Jobs job = jobsRepository.findById(jobId).get();
    job.setJobStatus(jobStatus
    );
    jobsRepository.save(job);

    //Jobs activeAndDeactive = jobsRepository.findActiveAndDeactive(jobId, jobStatus);

    return job;
  }

//__________For candidate get job on the basis of location and designation_________________________________________________

  public List<Jobs> getJobs_on_location_and_designation(String jobTitle, String jobLocation, JobFilter jobFilter) {
    List<Jobs> latestjobs = jobsRepository.findByJobTitleAndJobLocationAndJobFilter(jobTitle, jobLocation, jobFilter);
    return latestjobs;
  }

  //______________Get candidate job description _____________________________________________________________________________

  public Jobs getJobDescription(Long jobId) {
    try {
      Jobs jobDescription = jobsRepository.findById(jobId).get();
      return jobDescription;
    } catch (Exception e) {
      throw new BadReqException(ApiMessage.JOB_DESCRIPTION_NOT_FOUND);
    }
  }

//____________________Get latest job request by candidate _________________________________________________________________

    public List<Jobs> getLatestJob(String clientName,String jobLocation, String jobTitle, String skills,String shiftTime) {

      if(clientName.isEmpty()) {
        clientName=null;
      }
      if(jobLocation.isEmpty()) {
        jobLocation = null;
      }

      if(jobTitle.isEmpty()) {
      jobTitle=null;
      }

      if(skills.isEmpty()) {
        skills=null;
      }
      if(shiftTime.isEmpty()) {
        shiftTime = null;
      }
   List<Jobs> getLatestJobs = jobsRepository.findByJobSearches(clientName,jobLocation,jobTitle,skills,shiftTime);

      return getLatestJobs;

    }
    }

