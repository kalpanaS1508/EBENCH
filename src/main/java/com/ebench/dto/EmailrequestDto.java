package com.ebench.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailrequestDto {

    private Long candidateId;
    private Long vendorId;
    private Long appliedJobsId;
    private Long teamMemberId;


}
