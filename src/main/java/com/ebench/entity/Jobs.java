package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
@Data
@Table(name = "jobs")
@AllArgsConstructor
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
        public String postedDate;

        @Column(name = "expired_date")
        public String expiredDate;

        @Column(name = "interview_mode")
        public boolean interviewMode;

        @Column(name = "minimum_qualification")
        public String minimumQualification;

        @Column(name = "preffered_qualification")
        public String prefferedQualification;

        @Column(name = "candidate_selection")
        public boolean candidateSelection;

        @Column(name = "about_job")
        public String aboutJob;


    public Jobs() {
    }


}
