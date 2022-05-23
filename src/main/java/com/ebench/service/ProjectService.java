package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Candidate;
import com.ebench.entity.Project;
import com.ebench.entity.Vendor;
import com.ebench.exception.BadReqException;
import com.ebench.exception.ProjectNotFoundException;
import com.ebench.exception.ResourceNotFoundException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project createProject(Project project) throws ProjectNotFoundException {
        try {
            Project project1 = new Project();
            project1.setProjectName(project.getProjectName());
            project1.setCandidateId(project.getCandidateId());
            project1.setTaskId(project.getTaskId());
            project1.setVendorId(project.getVendorId());
            project1.setClientId(project.getClientId());
            project1.setDeleted(project.isDeleted());
            projectRepository.save(project1);
        } catch (Exception e) {
            throw new ProjectNotFoundException(ApiMessage.PROJECT_NOT_CREATED);
        }
        return project;
    }

    public Project updateProject(Project project,Long id)
    {
        Project project1 = projectRepository.findById(id).get();
        try {
            project1.setProjectName(project.getProjectName());
            project1.setCandidateId(project.getCandidateId());
            project1.setTaskId(project.getTaskId());
            project1.setVendorId(project.getVendorId());
            project1.setClientId(project.getClientId());
            project1.setDeleted(project.isDeleted());
            projectRepository.save(project1);
        }catch(Exception e)
        {
            throw new RuntimeException(ApiMessage.PROJECT_NOT_UPDATE_SUCESSFULLY);
        }
        return project;
    }

    public Project getProject(Long id){
        Optional<Project> project1 = projectRepository.findById(id);
        if(project1.isPresent()){
            Project project = project1.get();
            return project;
        }
        else{
            throw new BadReqException(ApiMessage.PROJECT_NOT_FOUND);
        }
    }

    public Project deleteProject(Long id) {
        Optional<Project> project =projectRepository.findById(id);
        Project project1= null;
        if(project.isPresent())
        {
            project1 = project.get();
        }
        else
        {
            throw new UserNotFoundException(ApiMessage.PROJECT_NOT_FOUND);
        }
        project1.setDeleted(false);
        projectRepository.save(project1);
        return project1;
    }



}