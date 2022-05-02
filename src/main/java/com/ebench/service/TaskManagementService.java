package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.TaskManagementReqDto;
import com.ebench.entity.TaskManagement;
import com.ebench.entity.User;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.TaskManagementRepository;
import com.ebench.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ebench.entity.UserType.*;

@Service
public class TaskManagementService {

    @Autowired
    public TaskManagementRepository taskManagementRepository;

    @Autowired
    public UserRepository userRepository;

    public TaskManagementReqDto addTaskManagement(TaskManagementReqDto taskManagement, Long loginUserId){
        Optional<User> user = userRepository.findById(loginUserId);
        User user1 = user.get();
        boolean users = user1.getUserType().equals(VENDOR);
        try {
            if (users == true) {
                TaskManagement taskManagement1 = new TaskManagement();
                taskManagement1.setCandidateId(taskManagement.getCandidateId());
                taskManagement1.setTaskName(taskManagement.getTaskName());
                taskManagement1.setAcceptance(taskManagement.isAcceptance());
                taskManagement1.setAddComment(taskManagement.getAddComment());
                taskManagement1.setTaskStatus(taskManagement.getTaskStatus());
                taskManagement1.setTaskOwnerId(taskManagement.getTaskOwnerId());
                taskManagement1.setDescription(taskManagement.getDescription());
                taskManagement1.setStartDate(taskManagement.getStartDate());
                taskManagement1.setDueDate(taskManagement.getDueDate());
                taskManagementRepository.save(taskManagement1);
            } else {
                throw new UserNotFoundException(ApiMessage.You_Have_Not_Authority);
            }
        }catch (UserNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        }

        return taskManagement;
    }

    public TaskManagementReqDto updateTaskmanagement(TaskManagementReqDto taskManagement){
        Optional<TaskManagement> taskManagement1 = taskManagementRepository.findById(taskManagement.getTaskId());
        TaskManagement taskManagement2 = new TaskManagement();
        taskManagement2 = taskManagement1.get();
        try{
            if(taskManagement1.isPresent()) {
                taskManagement2.setCandidateId(taskManagement.getCandidateId());
                taskManagement2.setTaskName(taskManagement.getTaskName());
                taskManagement2.setAcceptance(taskManagement.isAcceptance());
                taskManagement2.setAddComment(taskManagement.getAddComment());
                taskManagement2.setTaskStatus(taskManagement.getTaskStatus());
                taskManagement2.setTaskOwnerId(taskManagement.getTaskOwnerId());
                taskManagement2.setDescription(taskManagement.getDescription());
                taskManagementRepository.save(taskManagement2);
            }
            else {
                throw new UserNotFoundException(ApiMessage.User_Not_Present);
            }
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }

        return taskManagement;
    }
}
