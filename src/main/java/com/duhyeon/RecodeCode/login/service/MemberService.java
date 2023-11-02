package com.duhyeon.RecodeCode.login.service;

import com.duhyeon.RecodeCode.login.data.Dto.MemberDto;

import javax.mail.MessagingException;

public interface MemberService {
    void register(MemberDto memberDto) throws MessagingException;
}
