package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.*;
import com.ebench.exception.BadReqException;
import com.ebench.exception.ResourceNotFoundException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.CandidateRepository;
import com.ebench.repository.ProjectRepository;
import com.ebench.repository.TaskRepository;
import com.ebench.repository.VendorRepository;
import com.ebench.utils.GlobalResources;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class Taskservice {
    private Logger logger = GlobalResources.getlogger(Taskservice.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    VendorRepository vendorRepository;

    //_______________________________________GetProjects___________________________________________________________________________

    public Set<String> getProjectList(Long candidateId) {
        logger.info("fetching data from project repo by candidate id");
        Set<String> projectList = projectRepository.findByCandidateId(candidateId);
        if (projectList.size() < 1) {
            throw new BadReqException(ApiMessage.PROJECT_NOT_FOUND);
        }
        logger.info("getting project list");
        return projectList;
    }

    //_______________________________________________getalltaskslist_________________________________________________________________
    public List<String> getTask(Long candidateId ,String taskStartDate) {
        LocalDate date = LocalDate.parse(taskStartDate);

        DayOfWeek day = date.getDayOfWeek();

        System.out.println(day);

        List<String> tasks = taskRepository.findAllTasks(candidateId,taskStartDate);

        if (tasks.size() < 1) {
            throw new BadReqException(ApiMessage.Task_Not_Found);
        }
        logger.info("getting all task ");
        return tasks;
    }

    //___________________________________________getPendingTask_________________________________________________________________________________
    public List<String> getPendingTask(Long candidateId) {

        List<String> tasks = taskRepository.findPendingTasks(candidateId);
        if (tasks.size() < 1) {
            throw new BadReqException(ApiMessage.NO_PENDING_TASK);
        }
        logger.info("getting pending task");
        return tasks;
    }


    //_________________________________________________CreateTask_____________________________________________________________________________
    public Task createTask(Task taskmanagement) {
            if(!checkCandidateExist(taskmanagement.getCandidateId())) {
                throw new BadReqException(ApiMessage.THIS_CANDIDATE_ID_IS_NOT_PRESENT);
            }
            if(!checkVendorExist(taskmanagement.getVendorId())) {
                throw new BadReqException(ApiMessage.VENDOR_NOT_PRESENT);
            }
            if(!checkProjectExist(taskmanagement.getProjectId())) {
            throw new BadReqException(ApiMessage.PROJECT_NOT_FOUND);
            }
        logger.info("Task created sucessfullly");

        Task task = taskRepository.save(taskmanagement);
        return task;
    }


    //_______________________________Update Task____________________________________________
    public Task updateTask(Task taskmanagement) {

        if(taskmanagement.getTaskManagementId()==null)
        {
            throw new BadReqException(ApiMessage.TASK_ID_NULL);
        }
        else if(!taskRepository.existsById(taskmanagement.getTaskManagementId()))
        {
            throw new BadReqException(ApiMessage.PROVIDE_VALID_TASK_ID);
        }

        if(!checkCandidateExist(taskmanagement.getCandidateId())) {
            throw new BadReqException(ApiMessage.THIS_CANDIDATE_ID_IS_NOT_PRESENT);
        }
        if(!checkVendorExist(taskmanagement.getVendorId())) {
            throw new BadReqException(ApiMessage.VENDOR_NOT_PRESENT);
        }
        if(!checkProjectExist(taskmanagement.getProjectId())) {
            throw new BadReqException(ApiMessage.PROJECT_NOT_FOUND);
        }
        logger.info("Task updated sucessfullly");
        return taskRepository.save(taskmanagement);
    }


    //____________________________________________________Deletetask___________________________________________________________________
    public Task deleteTask(Long taskManagementId) {
        Optional<Task> task1 = taskRepository.findById(taskManagementId);

        Task taskmanagement = null;
        if (task1.isPresent()) {
            taskmanagement = task1.get();
        } else {
            throw new UserNotFoundException(ApiMessage.Task_Not_Found);
        }
        taskmanagement.setTaskStatus(false);

        taskRepository.save(taskmanagement);

        return taskmanagement;
    }

    //______________________________________GetTaskHistory________________________________________________________________________________
    //I have to do some chnge in it by (apply chnage tast sttaus as parameter

    public List<Task> getTaskHistory(Long candidateId) throws ResourceNotFoundException {

        List<Task> taskHistory = taskRepository.findTaskHistory(candidateId);
        if(taskHistory.isEmpty())
        {
            throw new ResourceNotFoundException(ApiMessage.TASK_HISTORY_NOT_FOUND);
        }
        logger.info("getTaskhistory");
        return taskHistory;
    }
//_________________________________PendingTaskForm___________________________________________________________________________________

    public Task pendingTaskForm(Long candidateId) {
        Task task = new Task();
        Task task1= taskRepository.findByTaskOwnerId(candidateId);
        if(task1!=null) {
            task1.setAnyDependency(task.getAnyDependency());
            task1.setWaitingResponseFrom(task.getWaitingResponseFrom());
            task1.setAddCommentsFromCandidate(task.getAddCommentsFromCandidate());
            task1.setTaskDescription(task.getTaskDescription());
            taskRepository.save(task1);
            logger.info("Pending task form");
        }
        else
        {
            throw new BadReqException(ApiMessage.Task_Not_Found);
        }
        return task;
    }
//________________________________________________PendingTaskByProjectName_______________________________________________________________
    public List<Task> getPendingTaskByProjectName(String projectName) {
        List<Task> taskList = taskRepository.findTaskProjectName(projectName);
        if(taskList.size()<1)
        {
            throw new BadReqException(ApiMessage.NO_PENDING_TASK);
        }
        return taskList;
    }

    public Boolean checkCandidateExist(Long id)
    {
        Optional<Candidate> candidateData = candidateRepository.findById(id);
        if(candidateData.isPresent())
        {
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkVendorExist(Long id)
    {
        Optional<Vendor> vendorData = vendorRepository.findById(id);
        if(vendorData.isPresent())
        {
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkProjectExist(Long id)
    {
        Optional<Project> projectData = projectRepository.findById(id);
        if(projectData.isPresent())
        {
            return true;
        }
        else{
            return false;
        }
    }

}