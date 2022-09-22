package com.ebench.dto.jobResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDto {

        private Long candidateId;
        private Long vendorId;
        private Long appliedJobsId;
        private Long teamMemberId;

    }
