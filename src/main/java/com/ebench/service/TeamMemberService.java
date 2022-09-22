package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Candidate;
import com.ebench.entity.Jobs;
import com.ebench.entity.TeamMember;
import com.ebench.exception.BadReqException;
import com.ebench.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

    @Service
    public class TeamMemberService {


        @Autowired
        TeamMemberRepository teamMemberRepository;


        //_______________add Team Member__________________________________//

        public TeamMember createTeam(TeamMember teamMember) {

            String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            boolean emailValidation = Pattern.compile(regexPattern)
                    .matcher(teamMember.getEmail())
                    .matches();

            if (emailAlreadyExist(teamMember.getEmail())) {
                throw new BadReqException(ApiMessage.EMAIL_ALREADY_USED);
            }

            TeamMember teamMember1 = new TeamMember();
            teamMember1.setFirstName(teamMember.getFirstName());
            teamMember1.setDesignation(teamMember.getDesignation());
            teamMember1.setLastName(teamMember.getLastName());
            teamMember1.setReportingManager(teamMember.getReportingManager());
            teamMember1.setTeamMemberId(teamMember.getTeamMemberId());
            teamMember1.setSkills(teamMember.getSkills());
            teamMember1.setValidity(teamMember.getValidity());
            teamMember1.setAddedBy(teamMember.getAddedBy());
            teamMember1.setStatus(teamMember.getStatus());
            teamMember1.setVendorId(teamMember.getVendorId());
            teamMember1.setNewTeamMemberId(teamMember.getNewTeamMemberId());

            if (!emailValidation) {
                throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
            } else {
                teamMember1.setEmail(teamMember.getEmail());
            }
            if (teamMember.getContactNumber1().isEmpty() || teamMember.getContactNumber1().length() != 10) {
                throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);
            } else {
                teamMember1.setContactNumber1(teamMember.getContactNumber1());
            }
            if (teamMember.getContactNumber2().isEmpty() || teamMember.getContactNumber2().length() != 10) {
                throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);
            } else {
                teamMember1.setContactNumber2(teamMember.getContactNumber2());
            }

            return teamMemberRepository.save(teamMember1);

        }


        public Boolean emailAlreadyExist(String email) {
            System.out.println("In Email Exist Checking Method");
            Optional<TeamMember> teamMember = teamMemberRepository.findByEmail(email);
            if (teamMember.isPresent()) {
                System.out.println("True");
                return true;
            } else {
                System.out.println("False");
                return false;
            }
        }

        //__________________________Team Member List_____________________________________//
        public List<TeamMember> getTeam() {

            List<TeamMember> teamMembersList = teamMemberRepository.findAll();
            return teamMembersList;
        }

        //_________________________updateTeam________________________________________//
        public TeamMember updateTeam(TeamMember teamMember) {

            Optional<TeamMember> teamMemberId = teamMemberRepository.findById(teamMember.getTeamMemberId());
            if (teamMemberId.isPresent()) {

                TeamMember teamMember2 = teamMemberId.get();
                System.out.println(teamMember2);

                if (teamMember.getTeamMemberId() != null) {
                    teamMember2.setTeamMemberId(teamMember.getTeamMemberId());
                }
                if (teamMember.getFirstName() != null) {
                    teamMember2.setFirstName(teamMember.getFirstName());
                }
                if (teamMember.getEmail() != null) {
                    teamMember2.setEmail(teamMember.getEmail());
                }
                if (teamMember.getDesignation() != null) {
                    teamMember2.setDesignation(teamMember.getDesignation());
                }
                if (teamMember.getReportingManager() != null) {
                    teamMember2.setReportingManager(teamMember.getReportingManager());
                }
                if (teamMember.getLastName() != null) {
                    teamMember2.setLastName(teamMember.getLastName());
                }
                if (teamMember.getNewTeamMemberId() != null) {
                    teamMember2.setNewTeamMemberId(teamMember.getNewTeamMemberId());
                }
                if (teamMember.getSkills() != null) {
                    teamMember2.setSkills(teamMember.getSkills());
                }
                if (teamMember.getValidity() != null) {
                    teamMember2.setValidity(teamMember.getValidity());
                }
                if (teamMember.getContactNumber2() != null) {
                    teamMember2.setContactNumber2(teamMember.getContactNumber2());
                }
                if (teamMember.getContactNumber1() != null) {
                    teamMember2.setContactNumber1(teamMember.getContactNumber1());
                }
                if (teamMember.getStatus() != null) {
                    teamMember2.setStatus(teamMember.getStatus());
                }
                if (teamMember.getVendorId() != null) {
                    teamMember2.setVendorId(teamMember.getVendorId());
                }
                return teamMemberRepository.save(teamMember2);
            } else {
                return teamMember;
            }
        }
    }

