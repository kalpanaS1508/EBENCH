package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateSkillsResDto;
import com.ebench.entity.CandidateSkills;
import com.ebench.exception.BadReqException;
import com.ebench.repository.CandidateSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateSkillsService {

    @Autowired
    CandidateSkillsRepository candidateSkillsRepository;

    public CandidateSkills addSkills(CandidateSkills candidateSkills){
        CandidateSkills candidateSkills1 = new CandidateSkills();
        candidateSkills1.setSkills(candidateSkills.getSkills());
        candidateSkills1.setExperience(candidateSkills.getExperience());
        candidateSkills1.setRating(candidateSkills.getRating());
        candidateSkills1.setCandidateId(candidateSkills.getCandidateId());

        CandidateSkills skills = candidateSkillsRepository.save(candidateSkills1);
        return skills;
    }

    public  CandidateSkills updateSkills(CandidateSkills candidateSkills){
        Optional<CandidateSkills> skillsId = candidateSkillsRepository.findById(candidateSkills.getCandidateSkillsId());

        CandidateSkills candidateSkills1 = null;
        if(skillsId.isPresent()){

             candidateSkills1 = skillsId.get();

             if(candidateSkills1.getCandidateSkillsId()  != null) {
                 candidateSkills1.setCandidateSkillsId(candidateSkills.getCandidateSkillsId());
             }

             if(candidateSkills.getSkills() !=null) {
                 candidateSkills1.setSkills(candidateSkills.getSkills());
             }

             if(candidateSkills.getExperience() !=null) {
                 candidateSkills1.setExperience(candidateSkills.getExperience());
             }

             if(candidateSkills.getRating() !=null) {
                 candidateSkills1.setRating(candidateSkills.getRating());
             }

             CandidateSkills skills = candidateSkillsRepository.save(candidateSkills1);
             return skills;
        }
        else {
            throw new BadReqException(ApiMessage.CANDIDATE_NOT_PRESENT);
        }
    }

    public List<CandidateSkillsResDto> getCandidateSkillList(Long candidateId)throws BadReqException {
        try {
            List<CandidateSkillsResDto> skills = candidateSkillsRepository.findByCandidateId(candidateId);
            return skills;
        } catch (Exception e) {
          throw  new BadReqException(ApiMessage.CANDIDATE_NOT_PRESENT);
        }
    }
    }
