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
import com.ebench.utils.GlobalResources;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    private Logger logger= GlobalResources.getlogger(ProjectService.class);

    @Autowired
    ProjectRepository projectRepository;

    public Project createProject(Project project) throws ProjectNotFoundException {
        Project project1 = new Project();
        try {
            project1.setProjectName(project.getProjectName());
            project1.setCandidateId(project.getCandidateId());
            project1.setTaskId(project.getTaskId());
            project1.setVendorId(project.getVendorId());
            project1.setClientId(project.getClientId());
            project1.setDeleted(project.isDeleted());
            projectRepository.save(project1);

        } catch (Exception e) {

            logger.error("project not created___________________________________________>_____________________________");
            throw new ProjectNotFoundException(ApiMessage.PROJECT_NOT_CREATED);
        }
        logger.info("project created sucessfully________________________________>_______________");
        return project1;
    }

    public Project updateProject(Project project,Long projectId)
    {
        logger.info("fetching data by id");
        Optional<Project> proj = projectRepository.findById(projectId);
        Project project1 = null;
        if(proj.isPresent()) {
             project1 = proj.get();

            try {
                project1.setProjectName(project.getProjectName());
                project1.setCandidateId(project.getCandidateId());
                project1.setTaskId(project.getTaskId());
                project1.setVendorId(project.getVendorId());
                project1.setClientId(project.getClientId());
                project1.setDeleted(project.isDeleted());
                logger.info("project saved sucessfully________________________________>-___________________________________");
                Project project2 = projectRepository.save(project1);
                return project2;

            } catch (Exception e) {
                logger.error("project has not created sucessfully_____________________________________>-_____________________________");
                throw new RuntimeException(ApiMessage.PROJECT_NOT_UPDATE_SUCESSFULLY);
            }
        }
        else
        {
            throw new BadReqException(ApiMessage.PROJECT_NOT_FOUND);
        }


    }

    public Project getProject(Long projectId)
    {  logger.error("fetching project details by projectId______________________________________________>-_____________________________________");
        Optional<Project> project1 = projectRepository.findById(projectId);
        if(project1.isPresent()){
            Project project = project1.get();
            return project;
        }
        else{
            logger.error("project not found______________>-____________________________");
            throw new BadReqException(ApiMessage.PROJECT_NOT_FOUND);
        }
    }

    public Project deleteProject(Long projectId) {
        logger.info("fetching project details by projectid from repository____________________>-______________");
        Optional<Project> project =projectRepository.findById(projectId);
        Project project1= null;
        if(project.isPresent())
        {
            project1 = project.get();
        }
        else
        {
            logger.error("project not found");
            throw new UserNotFoundException(ApiMessage.PROJECT_NOT_FOUND);
        }
        logger.info("deleted project__________>__________________________________________");
        project1.setDeleted(true);
        projectRepository.save(project1);
        return project1;
    }



}