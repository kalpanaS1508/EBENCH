package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.InterviewResDto;
import com.ebench.entity.Interview;
import com.ebench.exception.BadReqException;
import com.ebench.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {

    @Autowired
    InterviewRepository interviewRepository;

    public Interview createInterview(Interview interview) {

        Interview interview1 = new Interview();
        interview1.setInterviewId(interview.getInterviewId());
        interview1.setCandidateName(interview.getCandidateName());
        interview1.setCompanyName(interview.getCompanyName());
        interview1.setInterviewerId(interview.getInterviewerId());
        interview1.setInterviewerName(interview.getInterviewerName());
        interview1.setInteviewMode(interview.getInteviewMode());
        interview1.setInterviewLink(interview.getInterviewLink());
        interview1.setInterviewDate(interview.getInterviewDate());
        interview1.setInterviewStartTime(interview.getInterviewStartTime());
        interview1.setInterviewStartTime(interview.getInterviewStartTime());
        interview1.setCandidateStatus(interview.getCandidateStatus());
        interview1.setHiringManagerStatus(interview.getHiringManagerStatus());
        interview1.setHiringStatus(interview.getHiringStatus());
        interview1.setCandidateId(interview.getCandidateId());
        interview1.setJobId(interview.getJobId());

        Interview interview2 = interviewRepository.save(interview1);
        return interview2;
    }

    public Interview updateInterview(Interview interview) {

        Optional<Interview> interviewerId = interviewRepository.findById(interview.getInterviewId());
        Interview interview1 = null;

        try {
            if (interviewerId.isPresent()) {

                interview1 = interviewerId.get();
                interview1.setId(interview.getId());
                interview1.setInterviewerId(interview.getInterviewerId());
                interview1.setInterviewerName(interview.getInterviewerName());
                interview1.setInteviewMode(interview.getInteviewMode());
                interview1.setInterviewLink(interview.getInterviewLink());
                interview1.setInterviewDate(interview.getInterviewDate());
                interview1.setInterviewStartTime(interview.getInterviewStartTime());
                interview1.setInterviewStartTime(interview.getInterviewStartTime());
                interview1.setCandidateStatus(interview.getCandidateStatus());
                interview1.setHiringManagerStatus(interview.getHiringManagerStatus());
                interview1.setHiringStatus(interview.getHiringStatus());
                interview1.setCandidateId(interview.getCandidateId());
                interview1.setJobId(interview.getJobId());

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

    public List<InterviewResDto> getInterviewHistory(String hiringStatus) {

        List<InterviewResDto> hiringStatus1 = interviewRepository.findByHiringStatus(hiringStatus);

        System.out.println(hiringStatus1);
        return hiringStatus1;
    }

    public List<InterviewResDto> getInterviewPlannedHistory(Date interviewDate){

        List<InterviewResDto> date = interviewRepository.findByInterviewDate(interviewDate);
        return date;
    }
}
