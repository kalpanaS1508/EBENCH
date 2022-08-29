package com.ebench.dto.jobResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class MileStoneResDto {
    public Long sprintId;
    public String sprintName;
    public String mileStoneName;
    public Date sprintStartDate;
    public Date sprintEndDate;
    public String sprintDetails;
    public String status;
    public String sprintLink;
    public Long vendorId;
    public Long candidateId;
    public Integer payment;
    public Date milestoneStartDate;
    public Date milestoneEndDate;
    public Long totalSprints;

    public MileStoneResDto(  Long totalSprints ,Date milestoneStartDate, Date milestoneEndDate , String mileStoneName) {

        this.totalSprints = totalSprints;
        this.milestoneStartDate = milestoneStartDate;
        this.milestoneEndDate = milestoneEndDate;
        this.mileStoneName = mileStoneName;

    }
}
