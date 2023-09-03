package com.duhyeon.RecodeCode.login.service.impl;

import com.duhyeon.RecodeCode.login.data.Dto.AccountContext;
import com.duhyeon.RecodeCode.login.data.Dto.MemberDto;
import com.duhyeon.RecodeCode.login.data.Entity.Member;
import com.duhyeon.RecodeCode.login.data.Repository.MemberRepository;
import com.duhyeon.RecodeCode.login.service.MemberService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Member> member = memberRepository.findByMemberId(username);
        System.out.println("loadUserByUsername 실행");
        if (!member.isPresent()) {
            System.out.println("username : " + username);
            System.out.println("loadUserByUsername 실패");
            throw new UsernameNotFoundException(username);
        }
        else{
            System.out.println("loadUserByUsername 성공");
            Member getMember = member.get();

            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add((GrantedAuthority) () -> getMember.getAuth());
            AccountContext accountContext = new AccountContext(getMember, roles);
            return accountContext;
        }
    }


}
