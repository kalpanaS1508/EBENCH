package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

// --------------------------THIS TABLE USED FOR ADD CANDIDATE BY VENDOR--------------------------------------
@Entity
@Data
@Table(name = "team_member")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_MemberId")
    private Long teamMemberId;

    @Column(name="first_Name")
    private String firstName;

    @Column(name="last_Name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name="designation")
    private String designation;

    @Column(name = "reporting_Manager")
    private String reportingManager;

    @Column(name="skills")
    private String skills;

    @Column(name="validity")
    private String validity;

    @Column(name="contact_Number1")
    private String contactNumber1;

    @Column(name ="contact_Number2")
    private String contactNumber2;

    @Column(name="status")
    private Boolean status;

    @Column(name="candidateId")
    private Long newTeamMemberId;

    @Column(name ="vendorId")
    private Long vendorId;

    @Column(name="added_By")
    private String addedBy;


}

