package com.ebench.entity;

import com.ebench.Enums.HiringStatus;
import com.ebench.Enums.InteviewMode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "interview")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "hiring_status", columnDefinition = "enum('SELECTED','NOT_SELECTED','HIRING_IN_PROCESS','NEXT_ROUND_SCHEDULE')")
    @Enumerated(EnumType.STRING)
    public HiringStatus hiringStatus;

    public Integer rating;

    @Enumerated(EnumType.STRING)
    public InteviewMode inteviewMode;

}
