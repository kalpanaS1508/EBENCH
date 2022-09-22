package com.ebench.dto.jobResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDto {

    public Long jobId;
    public Long vendorId;
    public Long companyId;
    public String jobDescription;
    public String jobTitle;
    public String clientName;
    public String clientLocation;
    public String jobLocation;
    public boolean jobStatus;
    public String companyName;
    public Integer totalJobs;
    public Integer totalCandidate;
    public Integer noOfPosition;
    public String postedDate;
    public Integer resumeReceived;
    public boolean interviewMode;
    public String expiredDate;
    public String minimumQualification;
    public String prefferedQualification;
    public boolean candidateSelection;
    public String aboutJob;
    public String requiredSkills;
    public String requiredExperience;
    public Long numberOfResume;
    public Long numberOfCandidate;


    public JobResponseDto( Long jobId ,String jobTitle, String requiredSkills ,String jobLocation ,boolean jobStatus ,String companyName  ,String postedDate
                          , boolean candidateSelection ) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.requiredSkills = requiredSkills;
        this.jobLocation = jobLocation;
        this.jobStatus = jobStatus;
        this.companyName = companyName;
        this.postedDate = postedDate;
        this.candidateSelection = candidateSelection;

    }

    public JobResponseDto(Long jobId, Long numberOfCandidate ,String jobTitle, String requiredSkills , Long vendorId , String requiredExperience , String jobLocation) {
        this.jobId = jobId;
        this.numberOfCandidate = numberOfCandidate;
        this.jobTitle = jobTitle;
        this.requiredSkills = requiredSkills;
        this.vendorId = vendorId;
        this.requiredExperience= requiredExperience;
        this.jobLocation = jobLocation;
    }

    public JobResponseDto(Long jobId, Long vendorId, String jobTitle, String jobLocation, String requiredSkills,Integer totalCandidate, String requiredExperience) {
        this.jobId = jobId;
        this.vendorId = vendorId;
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.requiredSkills = requiredSkills;
        this.totalCandidate = totalCandidate;
        this.requiredExperience = requiredExperience;
    }
}
