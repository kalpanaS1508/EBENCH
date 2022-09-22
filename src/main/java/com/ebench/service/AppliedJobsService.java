package com.ebench.service;
import com.ebench.Apimessage.ApiMessage;
import com.ebench.Enums.RequestType;
import com.ebench.dto.jobResponseDto.AppliedJobResDto;
import com.ebench.dto.jobResponseDto.EmailRequestDto;
import com.ebench.entity.*;
import com.ebench.exception.BadReqException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class AppliedJobsService {

    @Value("${spring.mail.username}")
    public String email;

    @Value("${spring.mail.password}")
    public String password;

    @Autowired
    public JavaMailSender mailSender;

    @Autowired
    AppliedJobsRepository appliedJobsRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    VendorRepository vendorRepository;

    public AppliedJobs create(AppliedJobs appliedJobs) {

//        if(candidateAlreadyExist(appliedJobs.getCandidateId())){
//            throw new BadReqException("You have already applied on this job");
//        }

            AppliedJobs appliedJobs1 = new AppliedJobs();
            appliedJobs1.setJobId(appliedJobs.getJobId());
            appliedJobs1.setCandidateId(appliedJobs.getCandidateId());
            appliedJobs1.setJobAcceptanceStatus(appliedJobs.getJobAcceptanceStatus());


            AppliedJobs jobs = appliedJobsRepository.save(appliedJobs1);

            return jobs;

    }
    
    public List<AppliedJobResDto> getCandidateListAppliedJobs(Long jobId){
        List<AppliedJobResDto> appliedJob = appliedJobsRepository.findByJobId(jobId);
        return appliedJob;
    }


    public Boolean candidateAlreadyExist(Long candidateId) {
        boolean candidate = appliedJobsRepository.existsById(candidateId);
        System.out.println("--------------------- " + candidate);
        return true;
    }



    //___________________Create Notification_______________________________//

    public Notification createNotification(Notification notification) {
        try {
            Notification notification1 = notificationRepository.save(notification);
            return notification1;
        } catch (Exception e) {
            throw new BadReqException(ApiMessage.NOTIFICATION_NOT_CREATED);
        }
    }


    //__________________Get Notification________________________________//

    public List<Notification> getCandidateOnRequestBasis(String requestType)
    {

      List<Notification>notifications= notificationRepository.findByRequestType(RequestType.valueOf(requestType));

        return  notifications;

    }




    //_______________update status if accept __________________________//

       public AppliedJobs updateStatus(Long appliedJobsId ) {

        Optional<AppliedJobs> appliedJobsOptional = appliedJobsRepository.findById(appliedJobsId);

        System.out.println(appliedJobsOptional);

        AppliedJobs appliedJobs = null;

        if (appliedJobsOptional.isPresent()) {
            appliedJobs = appliedJobsOptional.get();
        }
        else {
            throw new UserNotFoundException("Candidate Not Found");
        }
        appliedJobs.setJobAcceptanceStatus(Boolean.TRUE);
        appliedJobsRepository.save(appliedJobs);
        return appliedJobs;

         }

    public List<Notification> getAllNotification() {
      List<Notification>notifications = notificationRepository.findAll();
      return notifications;
    }

//    ----------------------------------------UPDATE JOB STATUS-----------------------------------------------------


    public AppliedJobs updateStatus(Long appliedJobsId,Long candidateId) {

        AppliedJobs appliedJobsOptional = appliedJobsRepository.findByAppliedJobsIdAndCandidateId(appliedJobsId,candidateId);

        System.out.println(appliedJobsOptional);

        if (appliedJobsOptional!=null) {
            appliedJobsOptional.setJobAcceptanceStatus(Boolean.TRUE);
            return appliedJobsRepository.save(appliedJobsOptional);
        }
        else {
            throw new UserNotFoundException("Candidate Not Found");
        }

    }

// ----------------------------------- SEND EMAIL -------------------------------------------------------------------

    public String sendRequestEmail(EmailRequestDto emailrequestDto)
            throws MessagingException, UnsupportedEncodingException {
        try {

            Long candidateId = emailrequestDto.getCandidateId();
            Long appliedJobId = emailrequestDto.getAppliedJobsId();

            Optional<Candidate> candidate = candidateRepository.findById(emailrequestDto.getCandidateId());
            Optional<Vendor> vendor = vendorRepository.findById(emailrequestDto.getVendorId());

            String toAddress =candidate.get().getEmail();
            String fromVendor =vendor.get().getEmail();
            String subject = "Invitation for Interview";
            String content = "Dear " + candidate.get().getFirstName() + " " + candidate.get().getLastName() + ",<br>"
                    + "Thank you for applying to the "+ candidate.get().getJobProfile()+" position at <span style=\"color:#1e81b0\n\"><b>SHILSHA TECHNOLOGIES</b>:</span><br>"
                    +" After reviewing your application, we are excited to move forward with the interview process. <br> "
                    +" If you are interested then please click on accept button,<br><br>"
                    +" <a href= http://localhost:5000/ebench/update_job_status?id="+appliedJobId+"&candidateId="+candidateId
                    + "><button style=\"background-color:#76b5c5; padding: 5px 5px;\">Accept</button></a>&nbsp;&nbsp;"
                    + "<a href= http://localhost:5000/ebench/update_job_status?id="+appliedJobId+"&candidateId="+candidateId
                    + "><button style=\"background-color:white; padding: 5px 5px;\">Decline</button></a> <br><br><br><br><br>"
                    + " Thank you,<br>"
                    + "Have a nice day,<br>"
                    + "The Ebench Team";
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toAddress);
            helper.setFrom(fromVendor);
            helper.setSubject(subject);
            helper.setText(content, true);
            message.setContent(content, "text/html");
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Invitation sent successfully";
    }

}
