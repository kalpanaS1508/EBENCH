package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
@Data
@Valid
@Table(name = "jobs")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class Jobs {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "job_id")
        public Long jobId;

        @Column(name = "candidate_id")
        public Long candidateId;

        @Column(name = "vendor_id")
        public Long vendorId;

        @Column(name = "company_id")
        public Long companyId;

        @Column(name = "job_title")
        public String jobTitle;

        @Column(name = "client_name")
        public String clientName;

        @Column(name = "client_location")
        public String clientLocation;

        @Column(name = "job_location")
        public String jobLocation;

        @Column(name = "job_status")
        public boolean jobStatus;

        @Column(name = "company_name")
        public String companyName;

        @Column(name = "total_jobs")
        public Integer totalJobs;

        @Column(name = "total_candidate")
        public Integer totalCandidate;

        @Column(name = "no_of_position")
        public Integer noOfPosition;

        @Column(name = "posted_date")
        public Date postedDate;

        @Column(name = "resume_received")
        public Integer resumeReceived;

        @Column(name = "interview_mode")
        public boolean interviewMode;

    public Jobs() {
    }


    public Jobs(Long jobId, String jobTitle, boolean jobStatus, Date postedDate) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobStatus = jobStatus;
        this.postedDate = postedDate;
    }

    public Jobs(Long jobId, Long candidateId, Long vendorId, Long companyId, String jobTitle, String clientName, String clientLocation,
                String jobLocation, boolean jobStatus, String companyName, Integer totalJobs, Integer totalCandidate,
                Integer noOfPosition, Date postedDate, Integer resumeReceived, boolean interviewMode) {
        this.jobId = jobId;
        this.candidateId = candidateId;
        this.vendorId = vendorId;
        this.companyId = companyId;
        this.jobTitle = jobTitle;
        this.clientName = clientName;
        this.clientLocation = clientLocation;
        this.jobLocation = jobLocation;
        this.jobStatus = jobStatus;
        this.companyName = companyName;
        this.totalJobs = totalJobs;
        this.totalCandidate = totalCandidate;
        this.noOfPosition = noOfPosition;
        this.postedDate = postedDate;
        this.resumeReceived = resumeReceived;
        this.interviewMode = interviewMode;
    }
}
