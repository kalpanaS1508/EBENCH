package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.jobResponseDto.JobResponseDto;
import com.ebench.Enums.JobFilter;
import com.ebench.entity.Jobs;
import com.ebench.exception.BadReqException;
import com.ebench.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
      jobs1.setTotalJobs(jobs.getTotalJobs());
      jobs1.setTotalCandidate(jobs.getTotalCandidate());
      jobs1.setNoOfPosition(jobs.getNoOfPosition());
      jobs1.setPostedDate(jobs.getPostedDate());
      jobs1.setExpiredDate(jobs.getExpiredDate());
      jobs1.setInterviewMode(jobs.isInterviewMode());
      jobs1.setPrefferedQualification(jobs.getPrefferedQualification());
      jobs1.setMinimumQualification(jobs.getMinimumQualification());
      jobs1.setAboutJob(jobs.getAboutJob());
      jobs1.setCandidateSelection(jobs.isCandidateSelection());  // true = selected , false = not selected (by default)

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
        jobs1.setExpiredDate(jobs.getExpiredDate());
        jobs1.setInterviewMode(jobs.isInterviewMode());
        jobs1.setPrefferedQualification(jobs.getPrefferedQualification());
        jobs1.setMinimumQualification(jobs.getMinimumQualification());
        jobs1.setAboutJob(jobs.getAboutJob());
        jobs1.setCandidateSelection(jobs.isCandidateSelection());  // true = selected , false = not selected (by default)

        jobsRepository.save(jobs);

      } catch (Exception e) {
        throw new BadReqException(ApiMessage.JOB_NOT_PRESENT);
      }
    }
    if(!jobId.isPresent()){
      throw new BadReqException(ApiMessage.JOB_NOT_PRESENT);
    }
    return jobs;
  }

//------------------------------- JOB DELETE API (SOFT DELETE)------------------------------------------------

     public Jobs deleteJob(Long jobId) {
     Optional<Jobs> id = jobsRepository.findById(jobId);
       if(!id.isPresent()){
      throw new BadReqException(ApiMessage.JOB_NOT_PRESENT);
     }
     else{
       id.isPresent();
      Jobs jobs = id.get();
       jobsRepository.deleteById(jobs.getJobId());
    }
       return null;
     }

//  --------------------------------- GET LIST OF JOBS BY VENDOR ID ----------------------------------------------------

    public List<Jobs> getJobStatus(Long vendorId){
      List<Jobs> status = jobsRepository.findByJobStatus(vendorId);
      return status;
    }

//  ------------------------ JOB STATUS ACTIVE OR NOT ACTIVE BY JOB ID -------------------------------------------------

    public Jobs activeDeactiveStatus(Long jobId , boolean jobStatus){
      Jobs job = jobsRepository.findById(jobId).get();
      job.setJobStatus(jobStatus);
      jobsRepository.save(job);
      return job;
    }

//  -------------------------GET A LIST OF JOB HISTORY BY JOB STATUS----------------------------------------------------


    public List<JobResponseDto> manageJobHistory(boolean jobStatus){

      List<JobResponseDto> byStatus = jobsRepository.findByStatus(jobStatus);
      return byStatus;
    }

//    ------------------GET A LIST OF POSTED JOBS BY JOB STATUS , CANDIDATE SELECTION  AND POSTED DATE -----------------

  public List<JobResponseDto> postedJobs(boolean candidateSelection ,String jobTitle , String postedDate){

    if (jobTitle.isEmpty()) {
      jobTitle = null;
    }
    if (postedDate.isEmpty()) {
      postedDate = null;
    }

    List<JobResponseDto> postedJobs = jobsRepository.postedJobs(candidateSelection, jobTitle, postedDate);
    return postedJobs;
  }

  //__________For candidate get job on the basis of location and designation_________________________________________________

  public List<Jobs> getJobs_on_location_and_designation(String jobTitle, String jobLocation, JobFilter jobFilter) {
    List<Jobs> latestjobs;
    latestjobs = jobsRepository.findByJobTitleAndJobLocationAndJobFilter(jobTitle, jobLocation, jobFilter);
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



