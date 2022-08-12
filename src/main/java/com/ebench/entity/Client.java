package com.ebench.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Long vendorId;
    public Long candidateId;
    public Long projectId;
    public String clientName;


}
