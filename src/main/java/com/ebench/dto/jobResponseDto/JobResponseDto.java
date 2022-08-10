package com.ebench.dto.jobResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    public JobResponseDto(Long jobId, String jobTitle, boolean jobStatus, String postedDate) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobStatus = jobStatus;
        this.postedDate = postedDate;
    }

    public JobResponseDto(Long jobId, String jobTitle, String postedDate, boolean jobStatus, boolean candidateSelection) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.postedDate = postedDate;
        this.jobStatus = jobStatus;
        this.candidateSelection = candidateSelection;
    }
}
