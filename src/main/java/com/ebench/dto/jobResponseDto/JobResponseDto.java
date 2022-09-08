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


    public JobResponseDto( Long jobId ,String jobTitle, boolean jobStatus ,String postedDate,
                          String requiredSkills, String requiredExperience ,String jobLocation,String companyName) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobStatus = jobStatus;
        this.postedDate = postedDate;
        this.requiredSkills = requiredSkills;
        this.requiredExperience = requiredExperience;
        this.jobLocation = jobLocation;
        this.companyName = companyName;
    }

    public JobResponseDto(Long jobId, Long numberOfCandidate , Long numberOfResume,String jobTitle, String requiredSkills) {
        this.jobId = jobId;
        this.numberOfCandidate = numberOfCandidate;
        this.numberOfResume = numberOfResume;
        this.jobTitle = jobTitle;
        this.requiredSkills = requiredSkills;
    }
}
