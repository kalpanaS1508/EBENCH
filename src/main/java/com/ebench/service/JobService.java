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
      jobs1.setJobNameAndId(jobs.getJobNameAndId());
      jobs1.setVendorId(jobs.getVendorId());  //must
      jobs1.setCompanyId(jobs.getCompanyId());  //must
      jobs1.setJobDescription(jobs.getJobDescription());
      jobs1.setJobTitle(jobs.getJobTitle());
      jobs1.setRequiredExperience(jobs.getRequiredExperience());
      jobs1.setJobLocation(jobs.getJobLocation());
      jobs1.setJobStatus(jobs.isJobStatus());
      jobs1.setCompanyName(jobs.getCompanyName());
      jobs1.setNoOfPosition(jobs.getNoOfPosition());
      jobs1.setTotalCandidate(jobs.getTotalCandidate());
      jobs1.setPostedDate(jobs.getPostedDate());
      jobs1.setInterviewMode(jobs.getInterviewMode());
      jobs1.setAboutJob(jobs.getAboutJob());
      jobs1.setAboutCompany(jobs.getAboutCompany());
      jobs1.setRequiredSkills(jobs.getRequiredSkills());
      jobs1.setShiftTime(jobs.getShiftTime());
      jobs1.setInterviewRounds(jobs.getInterviewRounds());
      jobs1.setRole(jobs.getRole());
      jobs1.setRound1(jobs.getRound1());
      jobs1.setRound2(jobs.getRound2());
      jobs1.setRound3(jobs.getRound3());
      Jobs jobs2 = jobsRepository.save(jobs1);
      return jobs2;

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

        if(jobs.getJobId() !=null) {
          jobs1.setJobId(jobs.getJobId());
        }

        if(jobs.getJobNameAndId() != null) {
        jobs1.setJobNameAndId(jobs.getJobNameAndId());
       }

        if(jobs.getCandidateId() !=null) {
          jobs1.setCandidateId(jobs.getCandidateId());
        }

        if(jobs.getVendorId() !=null) {
          jobs1.setVendorId(jobs.getVendorId());
        }

        if(jobs.getCompanyId() !=null) {
          jobs1.setCompanyId(jobs.getCompanyId());
        }

        if(jobs.getJobDescription() !=null) {
          jobs1.setJobDescription(jobs.getJobDescription());
        }

        if(jobs.getClientName() !=null) {
          jobs1.setClientName(jobs.getClientName());

        }

        if(jobs.getClientLocation() !=null) {
          jobs1.setClientLocation(jobs.getClientLocation());
        }

        if(jobs.getJobLocation() !=null) {
          jobs1.setJobLocation(jobs.getJobLocation());
        }

          jobs1.setJobStatus(jobs.isJobStatus());


        if(jobs.getCompanyName() !=null) {
          jobs1.setCompanyName(jobs.getCompanyName());
        }

        if(jobs.getTotalJobs() !=null) {
          jobs1.setTotalJobs(jobs.getTotalJobs());
        }

        if(jobs.getRequiredExperience() != null) {
          jobs1.setRequiredExperience(jobs.getRequiredExperience());
        }

        if(jobs.getTotalCandidate() !=null) {
          jobs1.setTotalCandidate(jobs.getTotalCandidate());
        }

        if(jobs.getNoOfPosition() !=null) {
          jobs1.setNoOfPosition(jobs.getNoOfPosition());
        }

        if(jobs.getPostedDate() !=null) {
          jobs1.setPostedDate(jobs.getPostedDate());
        }

        if(jobs.getExpiredDate() !=null) {
          jobs1.setExpiredDate(jobs.getExpiredDate());
        }

        if(jobs.getInterviewMode() !=null) {
          jobs1.setInterviewMode(jobs.getInterviewMode());
        }

        if(jobs.getRequiredSkills() !=null) {
          jobs1.setRequiredSkills(jobs1.getRequiredSkills());
        }

        if(jobs.getPrefferedQualification() !=null) {
          jobs1.setPrefferedQualification(jobs.getPrefferedQualification());
        }

        if(jobs.getRole() !=null) {
            jobs1.setRole(jobs.getRole());
        }

        if(jobs.getMinimumQualification() !=null) {
          jobs1.setMinimumQualification(jobs.getMinimumQualification());
        }

        if(jobs.getAboutJob() !=null) {
          jobs1.setAboutJob(jobs.getAboutJob());
        }

        if(jobs.getRound1() !=null) {
          jobs1.setRound1(jobs.getRound1());
        }

        if(jobs.getRound2() !=null) {
          jobs1.setRound2(jobs.getRound2());
        }

        if(jobs.getRound3() !=null) {
          jobs1.setRound3(jobs.getRound3());
        }

        if(jobs.getAboutCompany() != null) {
            jobs1.setAboutCompany(jobs.getAboutCompany());
        }

        if(jobs.getJobTitle() !=null) {
            jobs1.setJobTitle(jobs.getJobTitle());
        }

        if(jobs.getShiftTime() != null) {
            jobs1.setShiftTime(jobs.getShiftTime());
        }

        if(jobs.getInterviewRounds() !=null) {
            jobs1.setInterviewRounds(jobs.getInterviewRounds());
        }

        jobs1.setCandidateSelection(jobs.isCandidateSelection());  // true = selected , false = not selected (by default)

        Jobs jobs2 = jobsRepository.save(jobs1);
        return jobs2;

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

//     -----------------------GET CREATED JOB LIST BY VENDOR ID WHICH VENDOR CREATED------------------------------------

    public List<JobResponseDto> getJobListByVendor(Long vendorId){
        List<JobResponseDto> jobListByVendor = jobsRepository.findByVendorId(vendorId);
        return jobListByVendor;
    }

//     ----------------------GET JOB VIEW DATA BY JOB ID---------------------------------------------

      public Jobs getJobsView(Long jobId) {
          Optional<Jobs> id = jobsRepository.findById(jobId);
          Jobs jobs = null;
          if(id.isPresent()) {
              jobs = id.get();
              Long previousJobCount = jobs.getJobView();
              Long currentJobCount = previousJobCount + 1L;
              jobs.setJobView(currentJobCount);
              jobsRepository.save(jobs);
          }
          return jobs;
      }



//  ------------------------  UPDATE JOB STATUS ACTIVE OR NOT ACTIVE BY JOB ID -------------------------------------------------

       public Jobs activeDeactiveStatus(Long jobId) {
         Optional<Jobs> Id = jobsRepository.findById(jobId);

         Jobs job = null;
         if (Id.isPresent()) {
           job = Id.get();
           job.setJobStatus(false);
           return jobsRepository.save(job);

         }
         else{
           throw new BadReqException(ApiMessage.VENDOR_NOT_PRESENT);
         }

       }


//  --------------------GET LIST OF JOBS BY JOB STATUS------------------------------------------------------------------

    public List<JobResponseDto> getNumberOfCandidate(boolean jobStatus , Long vendorId){
      List<JobResponseDto> status = jobsRepository.findByJobStatus(jobStatus , vendorId);
      return status;

    }



  //__________ GET JOB ON THE BASIS OF LOCATION AND DESCRIPTION---------------------------------------------------------

  public List<Jobs> getJobs_on_location_and_designation(String jobTitle, String jobLocation, JobFilter jobFilter) {
    List<Jobs> latestjobs;
    latestjobs = jobsRepository.findByJobDescriptionAndJobLocationAndJobFilter(jobTitle, jobLocation, jobFilter);
    return latestjobs;
  }

  //______________GET CANDIDATE JOB DESCRIPTION _____________________________________________________________________________

  public Jobs getJobDescription(Long jobId) {
    try {
      Jobs jobDescription = jobsRepository.findById(jobId).get();
      return jobDescription;
    } catch (Exception e) {
      throw new BadReqException(ApiMessage.JOB_DESCRIPTION_NOT_FOUND);
    }
  }

//____________________GET LATEST JOB REQUEST BY CANDIDATE _________________________________________________________________

//  public List<Jobs> getLatestJob(String clientName,String jobLocation, String jobTitle, String skills,String shiftTime) {
//
//    if(clientName.isEmpty()) {
//      clientName=null;
//    }
//    if(jobLocation.isEmpty()) {
//      jobLocation = null;
//    }
//
//    if(jobTitle.isEmpty()) {
//      jobTitle=null;
//    }
//
//    if(skills.isEmpty()) {
//      skills=null;
//    }
//    if(shiftTime.isEmpty()) {
//      shiftTime = null;
//    }
//    List<Jobs> getLatestJobs = jobsRepository.findByJobSearches(clientName,jobLocation,jobTitle,skills,shiftTime);
//
//    return getLatestJobs;
//  }

}



