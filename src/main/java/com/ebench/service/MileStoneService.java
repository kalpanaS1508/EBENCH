package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.jobResponseDto.MileStoneResDto;
import com.ebench.entity.MileStone;
import com.ebench.exception.BadReqException;
import com.ebench.repository.MileStoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MileStoneService {

    @Autowired
    MileStoneRepository mileStoneRepository;

    public MileStone addSprint(MileStone mileStone) throws RuntimeException {

        try {
            MileStone mileStone1 = new MileStone();
            mileStone1.setSprintName(mileStone.getSprintName());
            mileStone1.setMileStoneName(mileStone.getMileStoneName());
            mileStone1.setSprintStartDate(mileStone.getSprintEndDate());
            mileStone1.setSprintEndDate(mileStone.getSprintEndDate());
            mileStone1.setStatus(mileStone.getStatus());
            mileStone1.setSprintDetails(mileStone.getSprintDetails());
            mileStone1.setSprintLink(mileStone.getSprintLink());
            mileStone1.setCandidateId(mileStone.getCandidateId());
            mileStone1.setVendorId(mileStone.getVendorId());
            mileStone1.setMilestoneStartDate(mileStone.getMilestoneStartDate());
            mileStone1.setMilestoneEndDate(mileStone.getMilestoneEndDate());
            MileStone stone = mileStoneRepository.save(mileStone1);
            return stone;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mileStone;
    }

    public MileStone updateSprint(MileStone mileStone) {
        Optional<MileStone> id = mileStoneRepository.findById(mileStone.getMileStoneId());
        MileStone mileStone1 = null;

        try {
            if (id.isPresent()) {
                mileStone1 = id.get();
                mileStone1.setMileStoneId(mileStone.getMileStoneId());
                mileStone1.setMileStoneName(mileStone.getMileStoneName());
                mileStone1.setSprintName(mileStone.getSprintName());
                mileStone1.setSprintStartDate(mileStone.getSprintEndDate());
                mileStone1.setSprintEndDate(mileStone.getSprintEndDate());
                mileStone1.setStatus(mileStone.getStatus());
                mileStone1.setSprintDetails(mileStone.getSprintDetails());
                mileStone1.setSprintLink(mileStone.getSprintLink());
                mileStone1.setCandidateId(mileStone.getCandidateId());
                mileStone1.setVendorId(mileStone.getVendorId());
                MileStone stone = mileStoneRepository.save(mileStone1);
                return stone;
            } else {
                throw new BadReqException(ApiMessage.WORK_ORDER_IS_NOT_PRESENT);
            }

        } catch (BadReqException e) {
            throw new BadReqException(ApiMessage.WORK_ORDER_IS_NOT_PRESENT);
        }
    }

    public MileStone deleteMileStone(Long sprintId){
        mileStoneRepository.deleteById(sprintId);
        return null;
    }

    //--------- GET API OF COUNT TOTAL NO OF SPRINT OF MILESTONE BY CANDIDATE ID----------------------------------------

    public MileStoneResDto getTotalSprints(String mileStoneName){
        MileStoneResDto byCandidateId = mileStoneRepository.findByCandidateId(mileStoneName);
        return byCandidateId;
    }
}

