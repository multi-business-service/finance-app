package com.business.finance.service;

import com.business.finance.model.customer.CustomerInfoEntity;
import com.business.finance.repository.CustomerRepository;
import com.business.finance.request.MemberInfoRequest;
import com.business.finance.response.MemberInfoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    public MemberInfoResponse addCustomerInfo(MemberInfoRequest customerInfoRequest){
        CustomerInfoEntity customerInfoEntity = modelMapper.map(customerInfoRequest, CustomerInfoEntity.class);
        return memberInforResponse(customerRepository.save(customerInfoEntity));
    }

    private MemberInfoResponse memberInforResponse(CustomerInfoEntity customerInfoEntity){
        return modelMapper.map(customerInfoEntity, MemberInfoResponse.class);
    }
}
