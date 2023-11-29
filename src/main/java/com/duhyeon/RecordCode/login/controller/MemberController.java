package com.duhyeon.RecordCode.login.controller;

import com.duhyeon.RecordCode.login.data.Dto.MemberDto;
import com.duhyeon.RecordCode.login.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String register(MemberDto memberDto) {
        try {
            memberService.register(memberDto);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register";
        }
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
