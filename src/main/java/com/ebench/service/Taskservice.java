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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
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
    public Set<Project> getProjectList(Long candidateId) {
        logger.info("fetching data from project repo by candidate id");
        Set<Project> projectList = projectRepository.findByCandidateId(candidateId);
        if (projectList.size() < 1) {
            throw new BadReqException(ApiMessage.PROJECT_NOT_FOUND);
        }
        logger.info("getting project list");
        return projectList;
    }

    //_______________________________________________getalltaskslist_________________________________________________________________
    public List<Task> getTask(Long candidateId, String projectName, ChangeTaskStatus changeTaskStatus) {

//        List<Task> tasks = taskRepository.findAllTasks(candidateId,projectName,changeTaskStatus);

        List<Task> tasks = taskRepository.findByCandidateIdAndProjectNameAndChangeTaskStatus(candidateId, projectName, changeTaskStatus);

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

    //--------------------get This Week Pending Task------------------------------
    public List<Task> getPendingTaskByFilter(Long candidateId, String filterType) {

        if (filterType == "ThisWeek") {

            DayOfWeek firstDayOfWeek = WeekFields.of(Locale.ENGLISH).getFirstDayOfWeek();

            ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");

            LocalDate date = LocalDate.now(zoneid1).with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

            ZonedDateTime zonedDateTime = date.atStartOfDay(zoneid1);

            List<Task> tasks = taskRepository.findByTaskStartDateAfterAndCandidateId(Date.from(zonedDateTime.toInstant()), candidateId);
            if (tasks.size() < 1) {
                throw new BadReqException(ApiMessage.NO_PENDING_TASK);
            }
            logger.info("getting pending task");
            return tasks;
        }
        if (filterType == "ThisMonth") {
            ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");

            LocalDate date = LocalDate.now().withDayOfMonth(1);

            ZonedDateTime zonedDateTime = date.atStartOfDay(zoneid1);

            List<Task> tasks = taskRepository.findByTaskStartDateAfterAndCandidateId(Date.from(zonedDateTime.toInstant()), candidateId);
            if (tasks.size() < 1) {
                throw new BadReqException(ApiMessage.NO_PENDING_TASK);
            }
            logger.info("getting pending task");
            return tasks;
        }
        return null;
    }


    //_________________________________________________CreateTask_____________________________________________________________________________
    public Task createTask(Task taskmanagement) {
        if (!checkCandidateExist(taskmanagement.getCandidateId())) {
            throw new BadReqException(ApiMessage.THIS_CANDIDATE_ID_IS_NOT_PRESENT);
        }
        if (!checkVendorExist(taskmanagement.getVendorId())) {
            throw new BadReqException(ApiMessage.VENDOR_NOT_PRESENT);
        }
        if (!checkProjectExist(taskmanagement.getProjectId())) {
            throw new BadReqException(ApiMessage.PROJECT_NOT_FOUND);
        }
        logger.info("Task created sucessfullly");

        Task task = taskRepository.save(taskmanagement);
        return task;
    }


    //_______________________________Update Task____________________________________________
    public Task updateTask(Task taskmanagement) {



        if (taskmanagement.getTaskManagementId() == null) {
            throw new BadReqException(ApiMessage.TASK_ID_NULL);
        } else if (!taskRepository.existsById(taskmanagement.getTaskManagementId())) {
            throw new BadReqException(ApiMessage.PROVIDE_VALID_TASK_ID);
        }

        if (!checkCandidateExist(taskmanagement.getCandidateId())) {
            throw new BadReqException(ApiMessage.THIS_CANDIDATE_ID_IS_NOT_PRESENT);
        }
        if (!checkVendorExist(taskmanagement.getVendorId())) {
            throw new BadReqException(ApiMessage.VENDOR_NOT_PRESENT);
        }
        if (!checkProjectExist(taskmanagement.getProjectId())) {
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
        if (taskHistory.isEmpty()) {
            throw new ResourceNotFoundException(ApiMessage.TASK_HISTORY_NOT_FOUND);
        }
        logger.info("getTaskhistory");
        return taskHistory;
    }


    //_______________________________________________________________________________________________________________________________________________

    public Boolean checkCandidateExist(Long id) {
        Optional<Candidate> candidateData = candidateRepository.findById(id);
        if (candidateData.isPresent()) {
            System.out.println("True");
            return true;
        } else {
            System.out.println("False");
            return false;
        }
    }

    public Boolean checkVendorExist(Long id) {
        Optional<Vendor> vendorData = vendorRepository.findById(id);
        if (vendorData.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkProjectExist(Long id) {
        Optional<Project> projectData = projectRepository.findById(id);
        if (projectData.isPresent()) {
            return true;
        } else {
            return false;
        }
    }


    //______________________________________Get pending task of this week ______________________________________________________

    public List<Task> getTaskByDate(Long candidateId,String taskStartDate, String taskDueDate,ChangeTaskStatus changeTaskStatus ) {

        LocalDate startDate = LocalDate.parse(taskStartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse(taskDueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return taskRepository.findByCandidateIdAndTaskStartDateAndTaskDueDateAndChangeTaskStatus(candidateId,taskStartDate,taskDueDate,changeTaskStatus);

    }
}