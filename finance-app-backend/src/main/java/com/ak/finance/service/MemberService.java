package com.ak.finance.service;

import com.ak.finance.constants.FinanceAPIConstants;
import com.ak.finance.constrants.ExceptionEnumConstants;
import com.ak.finance.controller.constrants.AppEnumConstants;
import com.ak.finance.exception.FinanceRunTimeException;
import com.ak.finance.handler.AppExceptionHandler;
import com.ak.finance.model.MemberEntity;
import com.ak.finance.repository.MemberRepository;
import com.ak.finance.request.MemberInfoRequest;
import com.ak.finance.response.MemberResponse;
import com.ak.finance.response.MembersResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AppExceptionHandler appExceptionHandler;

    @Value("${application.name}")
    private String appName;


    /**
     * Adding new members into DB
     * @param memberInfoRequest contains member information
     * @return member added info message
     */
    public MemberResponse addMember(MemberInfoRequest memberInfoRequest){
        try {
            return memberAddedResponse(memberRepository.save(modelMapper.map(memberInfoRequest, MemberEntity.class)));
        }catch(Exception ex){
            throw  appExceptionHandler.handleDBException(ex);
        }
    }

    /**
     * Gives all members information
     * @return with all members information
     */
    public MembersResponse getAllMembers(){
        return getAllMemberResponse(memberRepository.findAll());
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
     * Deletes the member
     * @param mobileNo is user mobile number
     * @return delete info message
     */
    @Transactional
    public MemberResponse removeMember(BigInteger mobileNo){
        List<MemberEntity> entityResponse;
        try{
            entityResponse = memberRepository.removeByMobileNo(mobileNo);
        }catch(Exception ex){
            throw appExceptionHandler.handleDBException(ex);
        }
        return memberRemovedResponse(entityResponse);
    }
    private MemberResponse memberRemovedResponse(List<MemberEntity> memberEntities){
        if (CollectionUtils.isEmpty(memberEntities)){
            throw new FinanceRunTimeException(
                    ExceptionEnumConstants.Group.DATA_ERROR, ExceptionEnumConstants.Type.DATABASE_ERROR, ExceptionEnumConstants.Code.DATA_NOT_FOUND, ExceptionEnumConstants.Source.INTERNAL, ExceptionEnumConstants.Severity.LOW,
                    appName, "Data not exist", Timestamp.valueOf(LocalDateTime.now(FinanceAPIConstants.DEFAULT_ZONE_ID)),
                    null,  HttpStatus.BAD_REQUEST);
        }
        // memberEntities always has only one entry, so 0 index can be considered directly
        MemberResponse memberResponse = modelMapper.map(memberEntities.get(0), MemberResponse.class);
        memberResponse.setStatus(AppEnumConstants.Status.SUCCESS);
        memberResponse.setInfoMessage(memberResponse.getFirstName()
                .concat(" has deleted from ak finance. memberId:")
                .concat(memberResponse.getCuid())
                .concat( " and userId:")
                .concat(memberResponse.getMobileNo())
                .concat(" is deleted!"));
        return memberResponse;

    }

    private MemberResponse memberAddedResponse(MemberEntity memberEntity){
        MemberResponse memberResponse = modelMapper.map(memberEntity, MemberResponse.class);
        memberResponse.setStatus(AppEnumConstants.Status.SUCCESS);
        memberResponse.setInfoMessage(memberEntity.getFirstName()
                .concat(" has joined into ak finance. memberId:")
                .concat(memberEntity.getCuid())
                .concat( " and userId:")
                .concat(memberEntity.getMobileNo().toString())
                .concat(" is created!"));
        return  memberResponse;
    }

    private MembersResponse getAllMemberResponse(List<MemberEntity> memberList){

        // TO-DO isMemberIdRequire help on decide to send member id or not
        MembersResponse membersResponse = new MembersResponse();
        membersResponse.setMembersResponse(modelMapper.map(memberList, new TypeToken<List<MemberResponse>>() {}.getType()));
        return membersResponse;
    }
}
