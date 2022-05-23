package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.*;
import com.ebench.exception.BadReqException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.ProjectRepository;
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
    ProjectRepository projectRepository;

    //_______________________________________GetProjects___________________________________________________________________________
    public Set<String> getProjectList(Long candidateId) {
        Set<String> projectList = projectRepository.findByCandidateId(candidateId);
        if (projectList.size() < 1) {
            throw new BadReqException(ApiMessage.PROJECT_NOT_FOUND);
        }
        return projectList;
    }

    //_______________________________________________getalltaskslist_________________________________________________________________
    public List<String> getTask(Long candidateId) {

        List<String> tasks = taskRepository.findAllTasks(candidateId);
        if (tasks.size() < 1) {
            throw new BadReqException(ApiMessage.Task_Not_Found);
        }
        return tasks;
    }

    //___________________________________________getPendingTask_________________________________________________________________________________
    public List<String> getPendingTask(Long candidateId) {

        List<String> tasks = taskRepository.findPendingTasks(candidateId);
        if (tasks.size() < 1) {
            throw new BadReqException(ApiMessage.NO_PENDING_TASK);
        }
        return tasks;
    }


    //_________________________________________________CreateTask_____________________________________________________________________________
    public Task createTask(Task taskmanagement) {
        try {
            Task taskmanagement1 = new Task();
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
            taskmanagement1.setAddCommentsFromCandidate(taskmanagement.getAddCommentsFromCandidate());
            taskmanagement1.setWaitingResponseFrom(taskmanagement.getWaitingResponseFrom());
            taskmanagement1.setAnyDependency(taskmanagement.getAnyDependency());
            taskRepository.save(taskmanagement1);
        } catch (BadReqException e) {
            throw new BadReqException(ApiMessage.TASK_SUCESSFULLY_NOT_CREATED);
        }
        return taskmanagement;
    }


    //_______________________________Update Task____________________________________________
    public Task updateTask(Task taskmanagement, Long taskManagementId) {

        Task taskmanagement1 = taskRepository.findById(taskManagementId).get();
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
            taskmanagement1.setAddCommentsFromCandidate(taskmanagement.getAddCommentsFromCandidate());
            taskmanagement1.setWaitingResponseFrom(taskmanagement.getWaitingResponseFrom());
            taskmanagement1.setAnyDependency(taskmanagement.getAnyDependency());
            taskRepository.save(taskmanagement1);
        } catch (Exception e) {
            throw new BadReqException(ApiMessage.TASK_NOT_UPDATED_SUCESSFULLY);
        }
        return taskmanagement;
    }


    //____________________________________________________Deletetask___________________________________________________________________
    public Task deleteTask(Long taskManagementId) {
        Optional<Task> task1 = taskRepository.findById(taskManagementId);
        Task taskmanagement = null;
        if (task1.isPresent()) {
            taskmanagement = task1.get();
        } else {
            throw new UserNotFoundException(ApiMessage.TASK_NOT_DELETED_SUCESSFULLY);
        }
        taskmanagement.setTaskStatus(false);

        taskRepository.save(taskmanagement);

        return taskmanagement;
    }

    //______________________________________GetTaskHistory________________________________________________________________________________
    //I have to do some chnge in it by (apply chnage tast sttaus as parameter
    public List<Task> getTaskHistory(Long candidateId) {

        List<Task> taskHistory = taskRepository.findTaskHistory(candidateId);
        return taskHistory;
    }
//_________________________________PendingTaskForm___________________________________________________________________________________

    public Task pendingTaskForm(Task task,Long candidateId) {
        Task task1= taskRepository.findByTaskOwnerId(candidateId);
       task1.setAnyDependency(task.getAnyDependency());
       task1.setWaitingResponseFrom(task.getWaitingResponseFrom());
       task1.setAddCommentsFromCandidate(task.getAddCommentsFromCandidate());
       task1.setTaskDescription(task.getTaskDescription());
       taskRepository.save(task1);
       return task;
    }
//________________________________________________PendingTaskByProjectName_______________________________________________________________
    public List<Task> getPendingTaskByProjectName(String projectName) {
        List<Task> taskList = taskRepository.findTaskProjectName(projectName);
        return taskList;
    }
}