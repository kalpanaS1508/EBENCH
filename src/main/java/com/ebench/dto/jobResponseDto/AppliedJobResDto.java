package com.ebench.dto.jobResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppliedJobResDto {
    public Long appliedJobsId;
    public Long jobId;
    public Long candidateId;
    public String jobTitle;
    public String requiredSkills;
    public String skills;
    public String firstName;
    public Boolean jobAcceptanceStatus;

    public AppliedJobResDto(Long candidateId ,String firstName,String jobTitle, String skills , Long jobId , Boolean jobAcceptanceStatus) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.jobTitle = jobTitle;
        this.skills = skills;
        this.jobId = jobId;
        this.jobAcceptanceStatus = jobAcceptanceStatus;
    }

}
