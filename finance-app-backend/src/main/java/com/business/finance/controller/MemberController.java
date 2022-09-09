package com.business.finance.controller;


import com.business.finance.request.MemberInfoRequest;
import com.business.finance.response.MemberResponse;
import com.business.finance.response.MembersResponse;
import com.business.finance.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/finance")
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/add-member")
    public ResponseEntity<String> addMember(@RequestBody MemberInfoRequest memberInfoRequest) {
        return ResponseEntity.ok(memberService.addCustomerInfo(memberInfoRequest));
    }
    @GetMapping("/members")
    public ResponseEntity<MembersResponse> getAllMembers(@RequestParam(required = false) boolean isMemberIdRequire) {
        return ResponseEntity.ok(memberService.getAllMembers(isMemberIdRequire));
    }
}
