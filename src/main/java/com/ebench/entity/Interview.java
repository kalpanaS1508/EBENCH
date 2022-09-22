package com.ebench.entity;

import com.ebench.Enums.InterviewMode;
import com.ebench.Enums.InteviewType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.time.LocalDateTime;

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

    @Column(unique = true)
    public Long interviewId;
    public Long vendorId;
    public String candidateName;
    public Long candidateId;
    public Long appliedJobsId;
    public Long jobId;
    public String interviewerName;
    public Long teamMemberId;
    @Size(max = 10000)
    public String candidateResultInText;

    public String attachmentResume;

//    @JsonFormat(pattern = "yyyy-MM-dd")
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

    public LocalDateTime interviewSlot;

    @Enumerated(EnumType.STRING)
    public InteviewType inteviewType;

    @Column(name = "interview_mode")
    @Enumerated(EnumType.STRING)
    public InterviewMode interviewMode;

    public Integer round;

    @Column(name="is_Accepted")
    public Boolean isAcceptedByCandidate = false;
    public Boolean isAcceptedByTeammember = false;
}
