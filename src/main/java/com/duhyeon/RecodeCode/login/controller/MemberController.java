package com.duhyeon.RecodeCode.login.controller;

import com.duhyeon.RecodeCode.login.data.Dto.MemberDto;
import com.duhyeon.RecodeCode.login.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class MemberController {

    @Resource(name = "memberService")
    private MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity register(MemberDto memberDto) {
        try {
            memberService.register(memberDto);
            return ResponseEntity.status(200).body("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
