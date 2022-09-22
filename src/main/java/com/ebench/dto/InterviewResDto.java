package com.ebench.dto;

import com.ebench.Enums.InteviewType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;

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

//    @JsonFormat(pattern = "yyyy-MM-dd")
    public String interviewDate;

    @JsonFormat(pattern = "HH:mm:ss")
    public Time interviewStartTime;

    @JsonFormat(pattern = "HH:mm:ss")
    public Time interviewEndTime;
    public String interviewLink;
    public String candidateStatus;
    public String hiringManagerStatus;
    public Boolean reschedule;
    public String hiringStatus;
    public Integer rating;
    public String companyName;
    public String jobPosition;
    public Long vendorId;

    @Enumerated(EnumType.STRING)
    public InteviewType inteviewMode;

    public LocalDateTime interviewSlot;


    public InterviewResDto(Long interviewId, String interviewDate , String hiringStatus, Integer rating , String candidateName,
                           Long candidateId)
    {
        this.interviewId = interviewId;
        this.interviewDate = interviewDate;
        this.hiringStatus = hiringStatus;
        this.rating = rating;
        this.candidateName = candidateName;
        this.candidateId = candidateId;

    }

    public InterviewResDto(Long interviewId, String interviewDate, String hiringStatus,Integer rating, String candidateName
                           , String jobPosition , Long vendorId ,LocalDateTime interviewSlot, Boolean reschedule) {
        this.interviewId = interviewId;
        this.interviewDate = interviewDate;
        this.hiringStatus = hiringStatus;
        this.rating = rating;
        this.candidateName = candidateName;
        this.jobPosition = jobPosition;
        this.vendorId = vendorId;
        this.interviewSlot = interviewSlot;
        this.reschedule = reschedule;
    }
}
