package com.ak.finance.controller;


import com.ak.finance.request.MemberInfoRequest;
import com.ak.finance.response.MemberResponse;
import com.ak.finance.response.MembersResponse;
import com.ak.finance.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/finance")
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/add-member")
    public ResponseEntity<MemberResponse> addMember(@RequestBody MemberInfoRequest memberInfoRequest) {
        return ResponseEntity.ok(memberService.addMember(memberInfoRequest));
    }
    @GetMapping("/members")
    public ResponseEntity<MembersResponse> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/members/{mobileNo}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable BigInteger mobileNo) {
        return ResponseEntity.ok(memberService.getMember(mobileNo));
    }
    @DeleteMapping("/members/{mobileNo}")
    public ResponseEntity<MemberResponse> removeMember(@PathVariable BigInteger mobileNo) {
        return ResponseEntity.ok(memberService.removeMember(mobileNo));
    }
}
