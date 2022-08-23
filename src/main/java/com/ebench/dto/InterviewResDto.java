package com.ebench.dto;

import com.ebench.Enums.HiringStatus;
import com.ebench.Enums.InteviewMode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class InterviewResDto {

    public Long interviewId;
    public Long candidateId;
    public Long jobId;
    public String interviewerName;
    public Long interviewerId;
    public String interviewSlot;
    public String interviewLink;
    public String candidateStatus;
    public String hiringManagerStatus;
    public boolean reschedule;

    public String firstName;
    public String jobTitle;

    @Enumerated(EnumType.STRING)
    public HiringStatus hiringStatus;
    public Integer rating;

    @Enumerated(EnumType.STRING)
    public InteviewMode inteviewMode;


    public InterviewResDto(Long interviewId, String interviewSlot , HiringStatus hiringStatus, Integer rating ,String firstName,
                           Long candidateId, String jobTitle)
    {
        this.interviewId = interviewId;
        this.interviewSlot = interviewSlot;
        this.hiringStatus = hiringStatus;
        this.rating = rating;
        this.firstName = firstName;
        this.candidateId = candidateId;
        this.jobTitle = jobTitle;

    }


}
