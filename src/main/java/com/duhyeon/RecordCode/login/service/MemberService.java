package com.duhyeon.RecordCode.login.service;

import com.duhyeon.RecordCode.login.data.Dto.MemberDto;

import javax.mail.MessagingException;

public interface MemberService {
    void register(MemberDto memberDto) throws MessagingException;
}
