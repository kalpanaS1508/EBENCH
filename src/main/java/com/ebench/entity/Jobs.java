package com.ebench.entity;

import com.ebench.Enums.JobFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "jobs")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Jobs {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "job_id")
        public Long jobId;

        @Column(name = "job_name_and_id")
        public String jobNameAndId;

        @Column(name = "interview_id")
        public Long interviewId;

        @Column(name = "vendor_id")
        public Long vendorId;

        @Column(name = "company_id")
        public Long companyId;

        @Column(name = "job_title")
        public String jobTitle;

        @Column(name = "job_description")
        public String jobDescription;

        @Column(name = "interview_rounds")
        public Integer interviewRounds;

        @Column(name = "role")
        public String role;

        @Column(name = "client_name")
        public String clientName;

        @Column(name = "required_experience")
        public String requiredExperience;

        @Column(name = "client_location")
        public String clientLocation;

        @Column(name = "job_location")
        public String jobLocation;

        @Column(name = "job_status")
        public boolean jobStatus = true ;       // job status is about job is active or in active.

        @Column(name = "company_name")
        public String companyName;

        @Column(name = "total_jobs")
        public Integer totalJobs;

        @Column(name = "total_candidate")
        public Integer totalCandidate = 0;

        @Column(name = "no_of_position")
        public Integer noOfPosition;

        @Column(name = "posted_date")
        public String postedDate;

        @Column(name = "expired_date")
        public String expiredDate;

        @Column(name = "resume_received")
        public Integer resumeReceived = 0;

        @Column(name = "no_of_candidates")
        public Long noOfCandidates;

        @Column(name = "minimum_qualification")
        public String minimumQualification;

        @Column(name = "preffered_qualification")
        public String prefferedQualification;

        @Column(name = "candidate_selection")
        public boolean candidateSelection;

        @Column(name = "about_job")
        public String aboutJob;

        @Column(name = "about_company")
        public String aboutCompany;

        @Column(name = "job_view")
        public Long jobView = 0L ;


        @Enumerated(EnumType.STRING)
        public JobFilter jobFilter;

        @Column(name="shiftTime")
        public String shiftTime;

        @Column(name = "required_skills")
        public String requiredSkills;

        @Column(name = "job_code")
        public Integer jobCode;



}
