package com.ebench.dto;

import com.ebench.Enums.InteviewMode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class InterviewResDto {

    public Long id;

    public Long interviewId;
    public String candidateName;
    public Long candidateId;
    public Long jobId;
    public String interviewerName;
    public Long interviewerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date interviewDate;

    @JsonFormat(pattern = "HH:mm:ss")
    public Time interviewStartTime;

    @JsonFormat(pattern = "HH:mm:ss")
    public Time interviewEndTime;
    public String interviewLink;
    public String candidateStatus;
    public String hiringManagerStatus;
    public boolean reschedule;
    public String hiringStatus;
    public Integer rating;
    public String companyName;
    public String jobPosition;

    @Enumerated(EnumType.STRING)
    public InteviewMode inteviewMode;


    public InterviewResDto(Long interviewId, Date interviewDate , String hiringStatus, Integer rating ,String candidateName,
                           Long candidateId)
    {
        this.interviewId = interviewId;
        this.interviewDate = interviewDate;
        this.hiringStatus = hiringStatus;
        this.rating = rating;
        this.candidateName = candidateName;
        this.candidateId = candidateId;

    }

    public InterviewResDto(Long interviewId, Date interviewDate, String candidateName, Integer rating, String hiringStatus, String jobPosition) {
        this.interviewId = interviewId;
        this.interviewDate = interviewDate;
        this.candidateName = candidateName;
        this.rating = rating;
        this.hiringStatus = hiringStatus;
        this.jobPosition = jobPosition;
    }
}
