package com.ak.finance.service;

import com.ak.finance.model.MemberEntity;
import com.ak.finance.repository.MemberRepository;
import com.ak.finance.request.MemberInfoRequest;
import com.ak.finance.response.MemberResponse;
import com.ak.finance.response.MembersResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Adding new members into DB
     * @param memberInfoRequest contains member information
     * @return member added info message
     */
    public String addCustomerInfo(MemberInfoRequest memberInfoRequest){
        return memberAddedResponse(memberRepository.save(modelMapper.map(memberInfoRequest, MemberEntity.class)));
    }

    /**
     * Gives all members information
     * @param isMemberIdRequire helps to mask memberId
     * @return with all members information
     */
    public MembersResponse getAllMembers(boolean isMemberIdRequire){
        return getAllMemberResponse(memberRepository.findAll(), isMemberIdRequire);
    }

    /**
     * Gives the member info for requested user id
     * @param mobileNo is user mobile number
     * @return with member information
     */
    public MemberResponse getMember(BigInteger mobileNo){
        return modelMapper.map(memberRepository.findByMobileNo(mobileNo), MemberResponse.class);
    }

    /**
     * Deletes the users
     * @param mobileNo is user mobile number
     * @return delete info message
     */
    @Transactional
    public String removeMember(BigInteger mobileNo){
        return memberRepository.removeByMobileNo(mobileNo) == 1 ?
                "UserId ".concat(mobileNo.toString()).concat(" deleted successfully")
                : "No records found for user id:".concat(mobileNo.toString());
    }

    private String memberAddedResponse(MemberEntity memberEntity){
        return  memberEntity.getFirstName()
                .concat(" has joined into ak finance. memberId:")
                .concat(memberEntity.getCuid())
                .concat( "and userId:")
                .concat(memberEntity.getMobileNo().toString())
                .concat(" is created!");
    }

    private MembersResponse getAllMemberResponse(List<MemberEntity> memberList, boolean isMemberIdRequire){

        // TO-DO isMemberIdRequire help on decide to send member id or not
        MembersResponse membersResponse = new MembersResponse();
        membersResponse.setMembersResponse(modelMapper.map(memberList, new TypeToken<List<MemberResponse>>() {}.getType()));
        return membersResponse;
    }
}
