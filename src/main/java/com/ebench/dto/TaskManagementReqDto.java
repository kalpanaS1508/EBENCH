package com.ebench.dto;

import com.ebench.entity.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.annotation.DateTimeFormat;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@EnableJpaAuditing
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskManagementReqDto {
    public Long taskId;

    @Column(name = "task_name")
    public String taskName;

    @Column(name = "add_comment")
    @Size(max = 5000)
    public String addComment;

    @Column(name = "description")
    @Size(max = 5000)
    public String description;

    @Column(name = "acceptance")
    public boolean acceptance;

    @Column(name = "candidate_id")
    public Long candidateId;

    @Column(name = "task_owner_id")
    public Long taskOwnerId;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    public TaskStatus taskStatus;

    @CreatedDate
    @Column(name = "created_at")
    public LocalDate createdAt;


    @Column(name = "updated_at")
    @LastModifiedDate
    public LocalDateTime updatedAt;


    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date startDate;


    @Column(name = "due_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date dueDate;


    public TaskManagementReqDto() {
    }
}
