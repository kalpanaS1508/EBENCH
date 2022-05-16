package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Candidate;
import com.ebench.entity.ChangeTaskStatus;
import com.ebench.entity.Taskmanagement;
import com.ebench.exception.BadReqException;
import com.ebench.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class Taskservice {

    @Autowired
    TaskRepository taskRepository;

    public Set<String> getProjectList(Long candidateId) {
        Set<String> projectList = taskRepository.findByCandidateId(candidateId);
        if (projectList.size() < 1) {
            throw new BadReqException(ApiMessage.Task_Not_Found);
        }
        return projectList;
    }

    public List<Taskmanagement> getTask(Long id, String projectName, ChangeTaskStatus changeTaskStatus) {

        List<Taskmanagement> tasks = taskRepository.findAllTasks(id, projectName, changeTaskStatus);
        if (tasks.size() < 1) {
            throw new BadReqException(ApiMessage.Task_Not_Found);
        }
        return tasks;
    }

    public Taskmanagement createTask(Taskmanagement taskmanagement) {
        try {
            Taskmanagement taskmanagement1 = new Taskmanagement();
            taskmanagement1.setCandidateId(taskmanagement.getCandidateId());
            taskmanagement1.setVendorId(taskmanagement.getVendorId());
            taskmanagement1.setClientId(taskmanagement.getClientId());
            taskmanagement1.setCandidateName(taskmanagement.getCandidateName());
            taskmanagement1.setProjectName(taskmanagement.getProjectName());
            taskmanagement1.setProjectName(taskmanagement.getProjectName());
            taskmanagement1.setTaskName(taskmanagement.getTaskName());
            taskmanagement1.setTaskStartDate(taskmanagement.getTaskStartDate());
            taskmanagement1.setTaskDueDate(taskmanagement.getTaskDueDate());
            taskmanagement1.setTaskStatus(taskmanagement.isTaskStatus());
            taskmanagement1.setChangeTaskStatus(taskmanagement.getChangeTaskStatus());
            taskmanagement1.setNoOfDelayedDate(taskmanagement.getNoOfDelayedDate());
            taskmanagement1.setTaskDescription(taskmanagement.getTaskDescription());
            taskmanagement1.setAddCommentsFromClient(taskmanagement.getAddCommentsFromClient());
            taskmanagement.setAddCommentsFromCandidate(taskmanagement.getAddCommentsFromCandidate());
            taskRepository.save(taskmanagement1);
        } catch (BadReqException e) {
            throw new BadReqException(ApiMessage.TASK_SUCESSFULLY_CREATED);
        }
        return taskmanagement;
    }


    //_______________________________Update Task____________________________________________
    public Taskmanagement updateTask(Taskmanagement taskmanagement,Long taskManagementId) {

        Taskmanagement taskmanagement1 = taskRepository.findById(taskManagementId).get();
        System.out.println(taskmanagement1);
        try {
            taskmanagement1.setCandidateId(taskmanagement.getCandidateId());
            taskmanagement1.setVendorId(taskmanagement.getVendorId());
            taskmanagement1.setClientId(taskmanagement.getClientId());
            taskmanagement1.setCandidateName(taskmanagement.getCandidateName());
            taskmanagement1.setProjectName(taskmanagement.getProjectName());
            taskmanagement1.setProjectName(taskmanagement.getProjectName());
            taskmanagement1.setTaskName(taskmanagement.getTaskName());
            taskmanagement1.setTaskStartDate(taskmanagement.getTaskStartDate());
            taskmanagement1.setTaskDueDate(taskmanagement.getTaskDueDate());
            taskmanagement1.setTaskStatus(taskmanagement.isTaskStatus());
            taskmanagement1.setChangeTaskStatus(taskmanagement.getChangeTaskStatus());
            taskmanagement1.setNoOfDelayedDate(taskmanagement.getNoOfDelayedDate());
            taskmanagement1.setTaskDescription(taskmanagement.getTaskDescription());
            taskmanagement1.setAddCommentsFromClient(taskmanagement.getAddCommentsFromClient());
            taskmanagement.setAddCommentsFromCandidate(taskmanagement.getAddCommentsFromCandidate());
            taskRepository.save(taskmanagement1);
        }catch(Exception e)
        {
            throw new BadReqException(ApiMessage.TASK_UPDATED_SUCESSFULLY);
        }
            return taskmanagement;
}
}