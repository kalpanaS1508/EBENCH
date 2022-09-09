package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Applied_jobs")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppliedJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "applied_jobs_id")
    public Long appliedJobsId;

    @Column(name = "job_id")
    public Long jobId;

    @Column(name = "candidate_id")
    public Long candidateId;

    @Column(name = "job_acceptance_status")
    public Boolean jobAcceptanceStatus = false; // offer is not accepted by default


}
