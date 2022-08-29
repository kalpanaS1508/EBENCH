package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "job_work_order")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobWorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_work_order_id")
    public Long jobWorkOrderId;

    @Column(name = "candidate_id")
    public Long candidateId;

    @Column(name = "vendor_id")
    public Long vendorId;

    @Column(name = "billed_to")
    public Integer billedTo;

    @Column(name = "project_name")
    public String projectName;

    @Column(name = "job_work_order_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date jobWorkOrderDate;

    @Column(name = "contract_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date contractDate;

}
