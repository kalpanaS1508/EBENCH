package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.Enums.HiringStatus;
import com.ebench.dto.InterviewResDto;
import com.ebench.entity.Interview;
import com.ebench.exception.BadReqException;
import com.ebench.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {

    @Autowired
    InterviewRepository interviewRepository;

    public Interview createInterview(Interview interview) {

        Interview interview1 = new Interview();
        interview1.setInterviewerId(interview.getInterviewerId());
        interview1.setInterviewerName(interview.getInterviewerName());
        interview1.setInteviewMode(interview.getInteviewMode());
        interview1.setInterviewLink(interview.getInterviewLink());
        interview1.setInterviewSlot(interview.getInterviewSlot());
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
                interview1.setInterviewerId(interview.getInterviewerId());
                interview1.setInterviewerName(interview.getInterviewerName());
                interview1.setInteviewMode(interview.getInteviewMode());
                interview1.setInterviewLink(interview.getInterviewLink());
                interview1.setInterviewSlot(interview.getInterviewSlot());
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

    public List<InterviewResDto> getInterviewHistory(HiringStatus hiringStatus) {

        try {

            List<InterviewResDto> interviewResDto = interviewRepository.findByInterviewId(hiringStatus.name());
            return interviewResDto;
        }
        catch (Exception e) {
            System.out.printf("error" +e);
            throw new BadReqException(ApiMessage.INTERVIEW_NOT_PRESENT);
        }
    }
}
