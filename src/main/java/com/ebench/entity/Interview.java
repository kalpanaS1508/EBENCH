package com.ebench.entity;

import com.ebench.Enums.InteviewMode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "interview")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long interviewId;
    public String candidateName;
    public Long candidateId;
    public Long jobId;
    public String interviewerName;
    //public Long interviewerId;
    public Long teamMemberId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public String interviewDate;

    @JsonFormat(pattern = "HH:mm:ss")
    public Time interviewStartTime;

    @JsonFormat(pattern = "HH:mm:ss")
    public Time interviewEndTime;
    public String interviewLink;
    public String candidateStatus;
    public String hiringManagerStatus;
    public Boolean reschedule = false;
    public String hiringStatus;
    public Integer rating;
    public String companyName;
    public String jobPosition;
    public String attachmentResume;
    @Column(name="is_Accepted")
    public Boolean isAcceptedForFirstRound = false;
    public Boolean isAcceptedByTeammember = false;
    public Long vendorId;
    public Long appliedJobsId;


    @Enumerated(EnumType.STRING)
    public InteviewMode inteviewMode;

}
