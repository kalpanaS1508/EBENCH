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
@Table(name = "mile_stone")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MileStone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long mileStoneId;
    public String mileStoneName;
    public String sprintName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date sprintStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date sprintEndDate;

    public String sprintDetails;
    public String status;
    public String sprintLink;
    public Long vendorId;
    public Long candidateId;
    public Integer payment;

    @Column(name = "mile_stone_start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date milestoneStartDate;

    @Column(name = "mile_stone_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date milestoneEndDate;

}
