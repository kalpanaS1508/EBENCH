package com.ebench.dto.jobResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public Date postedDate;
    public Integer resumeReceived;
    public boolean interviewMode;

    public JobResponseDto(Long jobId, String jobTitle, boolean jobStatus, Date postedDate) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobStatus = jobStatus;
        this.postedDate = postedDate;
    }
}
