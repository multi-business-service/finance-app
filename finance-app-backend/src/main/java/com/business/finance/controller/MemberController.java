package com.business.finance.controller;


import com.business.finance.request.MemberInfoRequest;
import com.business.finance.response.MemberInfoResponse;
import com.business.finance.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/finance")
public class MemberController {

    @Autowired
    MemberService customerService;

    @PostMapping("/add-member")
    public ResponseEntity<MemberInfoResponse> addCustomer(@RequestBody MemberInfoRequest memberInfoRequest) {
        return ResponseEntity.ok(customerService.addCustomerInfo(memberInfoRequest));
    }
}
