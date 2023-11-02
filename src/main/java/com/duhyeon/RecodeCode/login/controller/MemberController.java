package com.duhyeon.RecodeCode.login.controller;

import com.duhyeon.RecodeCode.login.data.Dto.MemberDto;
import com.duhyeon.RecodeCode.login.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class MemberController {

    @Resource(name = "memberService")
    private MemberService memberService;

    @GetMapping(value = { "/", "/login" })
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
            return new ResponseEntity<>("회원가입에 성공했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
