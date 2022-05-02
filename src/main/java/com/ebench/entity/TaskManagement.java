package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(
//        value = {"createdAt", "updatedAt","startDate","dueDate"},
//        allowGetters = true
//)
@Table(name = "task_management")
public class TaskManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
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

    @Column(name = "created_at",updatable = false)
    @CreatedDate
    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;


    @Column(name = "updated_at")
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime updatedAt;


    @Column(name = "start_date",updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date startDate;


    @Column(name = "due_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date dueDate;


    public TaskManagement() {
    }

    public TaskManagement(Long taskId, String taskName, String addComment, String description, boolean acceptance, Long candidateId, Long taskOwnerId, TaskStatus taskStatus, Date startDate, Date dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.addComment = addComment;
        this.description = description;
        this.acceptance = acceptance;
        this.candidateId = candidateId;
        this.taskOwnerId = taskOwnerId;
        this.taskStatus = taskStatus;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
}
