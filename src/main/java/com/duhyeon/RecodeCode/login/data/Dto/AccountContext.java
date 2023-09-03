package com.duhyeon.RecodeCode.login.data.Dto;

import com.duhyeon.RecodeCode.login.data.Entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.annotation.Resource;
import java.util.Collection;

public class AccountContext extends User {

    @Resource(name = "member")
    private Member member;

    public AccountContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getMemberId(), member.getMemberPw(), authorities);
        this.member = member;
    }

}
