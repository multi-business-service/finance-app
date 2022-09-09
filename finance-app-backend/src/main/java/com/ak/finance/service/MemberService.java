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

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;
    public String addCustomerInfo(MemberInfoRequest customerInfoRequest){
        return memberAddedResponse(memberRepository.save(modelMapper.map(customerInfoRequest, MemberEntity.class)));
    }

    public MembersResponse getAllMembers(boolean isMemberIdRequire){
        return getAllMemberResponse(memberRepository.findAll(), isMemberIdRequire);
    }

    private String memberAddedResponse(MemberEntity customerInfoEntity){
        return  customerInfoEntity.getFirstName()
                .concat(" Joined successfully and member id ")
                .concat(customerInfoEntity.getCuid())
                .concat(" is created!");
    }

    private MembersResponse getAllMemberResponse(List<MemberEntity> memberList, boolean isMemberIdRequire){

        // TO-DO isMemberIdRequire help on decide to send member id or not

        MembersResponse membersResponse = new MembersResponse();
        membersResponse.setMembersResponse(modelMapper.map(memberList, new TypeToken<List<MemberResponse>>() {}.getType()));
        return membersResponse;
    }
}
