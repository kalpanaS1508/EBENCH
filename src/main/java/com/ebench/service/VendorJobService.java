package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Vendor;
import com.ebench.entity.VendorJobs;
import com.ebench.exception.BadReqException;
import com.ebench.repository.VendorJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorJobService {

  @Autowired
  public VendorJobsRepository vendorJobsRepository;

  public VendorJobs addJobs(VendorJobs vendorJobs) {

    VendorJobs jobs = new VendorJobs();
    try {
      jobs.setCandidateId(vendorJobs.getCandidateId());
      jobs.setVendorId(vendorJobs.getVendorId());
      jobs.setCompanyId(vendorJobs.getCompanyId());
      jobs.setJobTitle(vendorJobs.getJobTitle());
      jobs.setClientName(vendorJobs.getClientName());
      jobs.setClientLocation(vendorJobs.getClientLocation());
      jobs.setJobLocation(vendorJobs.getJobLocation());
      jobs.setJobStatus(vendorJobs.isJobStatus());
      jobs.setCompanyName(vendorJobs.getCompanyName());
      jobs.setTotalJobs(vendorJobs.getTotalJobs());
      jobs.setTotalCandidate(vendorJobs.getTotalCandidate());
      jobs.setNoOfPosition(vendorJobs.getNoOfPosition());
      jobs.setPostedDate(vendorJobs.getPostedDate());
      jobs.setResumeReceived(vendorJobs.getResumeReceived());
      jobs.setInterviewMode(vendorJobs.isInterviewMode());

      vendorJobsRepository.save(jobs);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return jobs;
  }

//  -----------------------VENDOR JOBS UPDATE--------------------------------------------------------------

  public VendorJobs updateJobs(VendorJobs vendorJobs) {
    Optional<VendorJobs> jobId = vendorJobsRepository.findById(vendorJobs.getJobId());

    if (jobId.isPresent()) {
      VendorJobs jobs = jobId.get();
      try {
        jobs.setCandidateId(vendorJobs.getCandidateId());
        jobs.setVendorId(vendorJobs.getVendorId());
        jobs.setCompanyId(vendorJobs.getCompanyId());
        jobs.setJobTitle(vendorJobs.getJobTitle());
        jobs.setClientName(vendorJobs.getClientName());
        jobs.setClientLocation(vendorJobs.getClientLocation());
        jobs.setJobLocation(vendorJobs.getJobLocation());
        jobs.setJobStatus(vendorJobs.isJobStatus());
        jobs.setCompanyName(vendorJobs.getCompanyName());
        jobs.setTotalJobs(vendorJobs.getTotalJobs());
        jobs.setTotalCandidate(vendorJobs.getTotalCandidate());
        jobs.setNoOfPosition(vendorJobs.getNoOfPosition());
        jobs.setPostedDate(vendorJobs.getPostedDate());
        jobs.setResumeReceived(vendorJobs.getResumeReceived());
        jobs.setInterviewMode(vendorJobs.isInterviewMode());

        vendorJobsRepository.save(jobs);
      } catch (Exception e) {
        throw new BadReqException(ApiMessage.VENDOR_JOB_NOT_PRESENT);
      }
    }
    if(!jobId.isPresent()){
      throw new BadReqException(ApiMessage.VENDOR_JOB_NOT_PRESENT);
    }
    return vendorJobs;
  }

//-------------------------------VENDOR DELETE API------------------------------------------------
     public VendorJobs deleteVendorJob(Long jobId) {

     Optional<VendorJobs> id = vendorJobsRepository.findById(jobId);

     if(!id.isPresent()){

      throw new RuntimeException(ApiMessage.VENDOR_JOB_NOT_PRESENT);
     }
     else{
      id.isPresent();
     VendorJobs vendorJobs = id.get();
     vendorJobsRepository.deleteById(vendorJobs.getVendorId());
    }
       return null;
     }
}

