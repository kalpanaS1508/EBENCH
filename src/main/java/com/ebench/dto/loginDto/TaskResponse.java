package com.ebench.dto.loginDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    @Column(name="taskStartDate")
    public Date taskStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="taskDueDate")
    public Date taskDueDate;
    @Column(name="taskStatus")
    public boolean taskStatus;
    @Column(name="noOfDelayedDate")
    public Long noOfDelayedDate;
    @Size(max=300)
    @Column(name="taskDescription")
    public String taskDescription;
    @Column(name="addCommentsFromClient")
    public String addCommentsFromClient;
    @Column(name="addCommentsFromCandidate")
    public String addCommentsFromCandidate;
    @Column(name="waitingResponseFrom")
    public String waitingResponseFrom;
    @Column(name="anyDependency")
    public String anyDependency;



}
