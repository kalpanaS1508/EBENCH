package com.ebench.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

@Data
@Valid
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class CandidateSkillsResDto {
    public Long candidateSkillsId;
    public String skills;
    public String experience;
    public Integer rating;
    public Long candidateId;
    public String firstName;

    public CandidateSkillsResDto(Long candidateSkillsId, String skills, Integer rating,String experience, String firstName ,Long candidateId) {
        this.candidateSkillsId = candidateSkillsId;
        this.skills = skills;
        this.rating = rating;
        this.experience = experience;
        this.firstName = firstName;
        this.candidateId = candidateId;
    }
}
