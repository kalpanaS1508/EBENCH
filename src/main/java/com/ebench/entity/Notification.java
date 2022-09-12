package com.ebench.entity;

import com.ebench.Enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    private Long candidateId;
    private Long jobId;
    @Enumerated(EnumType.STRING)
    private RequestType requestType;
    private Boolean status;
    private Long vendorId;
    private Long appliedJobId;


}
