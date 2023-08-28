package com.duhyeon.RecodeCode.login.service.impl;

import com.duhyeon.RecodeCode.login.data.Dto.MemberDto;
import com.duhyeon.RecodeCode.login.data.Entity.Member;
import com.duhyeon.RecodeCode.login.data.Repository.MemberRepository;
import com.duhyeon.RecodeCode.login.service.MemberService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("memberService")
public class MemberServiceImpl implements UserDetailsService,MemberService {

    @Resource(name="memberRepository")
    private MemberRepository memberRepository;

    @Override
    public void register(MemberDto memberDto) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDto.setMemberPw(encoder.encode(memberDto.getMemberPw()));

        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setMemberPw(memberDto.getMemberPw());
        member.setMemberName(memberDto.getMemberName());
        member.setMemberPhone(memberDto.getMemberPhone());
        member.setMemberEmail(memberDto.getMemberEmail());
        member.setMemberAddress(memberDto.getMemberAddress());
        member.setAuth(memberDto.getAuth());

        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
