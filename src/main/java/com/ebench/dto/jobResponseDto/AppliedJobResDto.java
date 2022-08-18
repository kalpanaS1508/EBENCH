package com.ebench.dto.jobResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AppliedJobResDto {
    public Long appliedJobsId;
    public Long jobId;
    public Long candidateId;
    public String jobTitle;
    public Long resumeReceived;
    public Long numberOfResume;
    public Long numberOfCandidate;


    public AppliedJobResDto( Long numberOfResume, Long numberOfCandidate ,String jobTitle, Long jobId, Long resumeReceived) {
        this.numberOfResume = numberOfResume;
        this.numberOfCandidate = numberOfCandidate;
        this.jobTitle = jobTitle;
        this.jobId = jobId;
        this.resumeReceived = resumeReceived;

    }
}
