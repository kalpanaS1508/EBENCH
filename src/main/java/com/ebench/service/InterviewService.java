package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.InterviewResDto;
import com.ebench.entity.Candidate;
import com.ebench.entity.Interview;
import com.ebench.entity.TeamMember;
import com.ebench.entity.Vendor;
import com.ebench.exception.BadReqException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.CandidateRepository;
import com.ebench.repository.InterviewRepository;
import com.ebench.repository.TeamMemberRepository;
import com.ebench.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {

    @Value("${spring.mail.username}")
    private String email;

    @Value("${spring.mail.password}")
    private String password;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    TeamMemberRepository teamMemberRepository;

    public Interview createInterview(Interview interview) throws MessagingException, UnsupportedEncodingException {

        Optional<Interview> interviewId = interviewRepository.findById(interview.getInterviewId());


        Interview interview1 = new Interview();
        interview1.setInterviewId(interview.getInterviewId());
        interview1.setVendorId(interview.getVendorId());

        if(interviewId.isPresent()){
            throw new BadReqException("interview id should be unique");
        } else {
            interview1.setTeamMemberId(interview.getInterviewId());
        }

        interview1.setCandidateName(interview.getCandidateName());
        interview1.setCompanyName(interview.getCompanyName());
        interview1.setInterviewerName(interview.getInterviewerName());
        interview1.setInterviewMode(interview.getInterviewMode());
        interview1.setInteviewType(interview.getInteviewType());
        interview1.setInterviewLink(interview.getInterviewLink());
        interview1.setInterviewDate(interview.getInterviewDate());
        interview1.setInterviewStartTime(interview.getInterviewStartTime());
        interview1.setInterviewStartTime(interview.getInterviewStartTime());
        interview1.setCandidateStatus(interview.getCandidateStatus());
        interview1.setHiringManagerStatus(interview.getHiringManagerStatus());
        interview1.setHiringStatus(interview.getHiringStatus());
        interview1.setCandidateId(interview.getCandidateId());
        interview1.setJobId(interview.getJobId());
        interview1.setReschedule(interview.getReschedule());
        interview1.setRound(interview.getRound());


        Interview interview2 = interviewRepository.save(interview1);
        sentInvitationEmail(interview1);
        return interview2;
    }


    public List<InterviewResDto> getInterviewPlanned(String hiringStatus) {

        List<InterviewResDto> hiringStatus1 = interviewRepository.findByHiringStatus(hiringStatus);

        System.out.println(hiringStatus1);
        return hiringStatus1;
    }

    public List<InterviewResDto> getInterviewHistory(String interviewDate){
        List<InterviewResDto> date = interviewRepository.findByInterviewDate(interviewDate);
        return date;
    }

    public List<InterviewResDto> getInterviewPlannedHistory(String interviewDate){
        List<InterviewResDto> date = interviewRepository.findByIntDate(interviewDate);
        return date;
    }



    //_______________________Email sent to candidate ____________________________//

    public void sentInvitationEmail(Interview interview)
            throws MessagingException, UnsupportedEncodingException {
        try {

            Optional<Candidate> candidate = candidateRepository.findById(interview.getCandidateId());

            if(!candidate.isPresent()){
                throw new UserNotFoundException("Candidate Not Found!");
            }

            Optional<Vendor> vendor = vendorRepository.findById(interview.getVendorId());
            if(!vendor.isPresent()){
                throw new UserNotFoundException("Vendor Not Found!");
            }

            Optional<TeamMember> teamMember = teamMemberRepository.findById(interview.getTeamMemberId());
            if(!teamMember.isPresent()){
                throw new UserNotFoundException("TeamMember Not Found!");
            }

            Optional<Interview> interview1 = interviewRepository.findById(interview.getId());
            if(!interview1.isPresent())
            {
                throw new UserNotFoundException("Interview not found");
            }


            sendEmailToCandidate(candidate.get(), vendor.get(),interview1.get());
            sendEmailToTeamMember(teamMember.get(), vendor.get(),candidate.get(),interview1.get());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendEmailToTeamMember(TeamMember teamMember, Vendor vendor,Candidate candidate,Interview interview) throws MessagingException {

        Long interviewId = interview.getInterviewId();
        Long teamMemberId= teamMember.getTeamMemberId();


        String fromVendor =vendor.getEmail();
        String subject = "Invitation for Interview";
        String content = "Dear " + teamMember.getFirstName() + " " + teamMember.getLastName() + ",<br>"
                + "Today we have scheduled interview of " + candidate.getFirstName() +" "+candidate.getLastName()+"for"+candidate.getJobProfile()+"profile<br>"
                +"Interview Scheduling date and timing is "+interview.getInterviewDate()+""+interview.getInterviewStartTime()+"<br>"
                +" <a href= http://localhost:5000/ebench/update_Status?interviewId="+interviewId+"&teamMemberId="+teamMemberId
                +"><button style=\"background-color:#76b5c5; padding: 5px 5px;\">Accept</button></a> &nbsp;&nbsp;;"
                +"<button style=\"background-color:white; padding: 5px 5px;\">Decline</button></a> <br><br><br><br><br>;"
                + " Thank you,<br>"
                + "Have a nice day,<br>"
                + "The Ebench Team";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(teamMember.getEmail());
        helper.setFrom(fromVendor);
        helper.setSubject(subject);
        helper.setText(content, true);

        //  FileSystemResource file = new FileSystemResource(new File(interview.getAttachmentResume()));
        message.setContent(content, "text/html");
        mailSender.send(message);

    }

    private void sendEmailToCandidate(Candidate candidate, Vendor vendor,Interview interview) throws MessagingException {

        Long interviewId= interview.getInterviewId();
        Long candidateId = interview.getCandidateId();
        String fromVendor =vendor.getEmail();
        String subject = "Invitation for Interview";
        String content = "Dear " + candidate.getFirstName() + " " + candidate.getLastName() + ",<br>"
                + "Thank you for accepting job Request  to the "+ candidate.getJobProfile()+" position at <span style=\"color:#1e81b0\n\"><b>SHILSHA TECHNOLOGIES</b>:</span><br>"
                +"<I>We want to invite you for an interview for the role of "+ candidate.getJobProfile() + "at our office premises in this address</I> <br> "
                + vendor.getAddress()+" "+ "at scheduling date"+interview.getInterviewDate()+"and starting time is "+interview.getInterviewStartTime()+"."+
                ",<br><br><br>"
                +"If you need to reschedule to a different time and/or date, please respond to this message by click on reshedule button ,so we can accommodate you.<br>" +
                " Let me know if you have any additional questions. We look forward to meeting with you.<br><br>"

                +" <a href= http://localhost:5000/ebench/update_Teammember_status?interviewId="+interviewId+"&candidateId="+candidateId
                +"><button style=\"background-color:#76b5c5; padding: 5px 5px;\">Accept</button></a>&nbsp;&nbsp;"
                +"<button style=\"background-color:white; padding: 5px 5px;\">Decline</button></a> <br><br><br><br><br>;"
                + " Thank you,<br>"
                + "Have a nice day with Best wishes,<br>"
                + "The Ebench Team";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(candidate.getEmail());
        helper.setFrom(fromVendor);
        helper.setSubject(subject);
        helper.setText(content, true);
        message.setContent(content, "text/html");
        mailSender.send(message);

    }


    public Interview updateInterview(Interview interview) {

        Optional<Interview> interviewerId = interviewRepository.findById(interview.getId());
        Interview interview1 = null;

        try {
            if (interviewerId.isPresent()) {

                interview1 = interviewerId.get();
                if(interview.getAttachmentResume()!=null) {
                    interview1.setAttachmentResume(interview.getAttachmentResume());
                }
                if(interview.getInterviewId()!=null) {
                    interview1.setInterviewId(interview.getInterviewId());
                }
                if(interview.getCandidateName()!=null) {
                    interview1.setCandidateName(interview.getCandidateName());
                }
                if(interview.getCompanyName()!=null) {
                    interview1.setCompanyName(interview.getCompanyName());
                }
                // interview1.setInterviewerId(interview.getInterviewerId());
                if(interview.getTeamMemberId()!=null) {
                    interview1.setTeamMemberId(interview.getTeamMemberId());
                }
                if(interview.getInterviewMode()!=null) {
                    interview1.setInterviewMode(interview.getInterviewMode());
                }
                if(interview.getInterviewLink()!=null) {
                    interview1.setInterviewLink(interview.getInterviewLink());
                }
                if(interview.getInterviewDate()!=null) {
                    interview1.setInterviewDate(interview.getInterviewDate());
                }
                if(interview.getInterviewStartTime()!=null) {
                    interview1.setInterviewStartTime(interview.getInterviewStartTime());
                }
                if(interview.getInterviewEndTime()!=null) {
                    interview1.setInterviewEndTime(interview.getInterviewEndTime());
                }
                if(interview.getHiringStatus()!=null) {
                    interview1.setHiringStatus(interview.getHiringStatus());
                }
                if(interview.getCandidateId()!=null) {
                    interview1.setCandidateId(interview.getCandidateId());
                }
                if(interview.getJobId()!=null) {
                    interview1.setJobId(interview.getJobId());
                }
                if(interview.getAppliedJobsId()!=null) {
                    interview1.setAppliedJobsId(interview.getAppliedJobsId());
                }
                if(interview.getVendorId()!=null) {
                    interview1.setVendorId(interview.getVendorId());
                }
                if(interview.getIsAcceptedByCandidate()!=null) {
                    interview1.setIsAcceptedByCandidate(interview.getIsAcceptedByCandidate());
                }
                if(interview.getReschedule()!=null) {
                    interview1.setReschedule(interview.getReschedule());
                }
                if(interview.getCandidateResultInText()!=null) {
                    interview1.setCandidateResultInText(interview.getCandidateResultInText());
                }
                if(interview.getRating()!=null)
                {
                    interview1.setRating(interview.getRating());
                }

                Interview save = interviewRepository.save(interview1);
                return save;
            } else {
                throw new BadReqException(ApiMessage.INTERVIEW_NOT_PRESENT);
            }
        } catch (BadReqException e) {
            e.printStackTrace();
        }
        return interview1;
    }


    public Interview updateStatus(Long interviewId, Long candidateId) {

        Interview interview = interviewRepository.findByInterviewIdAndCandidateId(interviewId,candidateId);

        System.out.println(interview);

        if (interview!=null) {
            interview.setIsAcceptedByCandidate(Boolean.TRUE);
            return interviewRepository.save(interview);
        }
        else {
            throw new UserNotFoundException("Candidate Not Found");
        }

    }


    public Interview update_TeamMember_Apply_status(Long interviewId,Long teamMemberId)
    {

        Interview interview = interviewRepository.findByInterviewIdAndTeamMemberId(interviewId,teamMemberId);
        System.out.println(interview);


        if (interview!=null) {
            interview.setIsAcceptedByTeammember(Boolean.TRUE);
            return interviewRepository.save(interview);
        }
        else {
            throw new UserNotFoundException("TeamMemberId Not Found");
        }
    }

}


