package com.duhyeon.RecordCode.login.service.impl;

import com.duhyeon.RecordCode.login.data.Dto.AccountContext;
import com.duhyeon.RecordCode.login.data.Dto.MemberDto;
import com.duhyeon.RecordCode.login.data.Entity.Member;
import com.duhyeon.RecordCode.login.data.Repository.MemberRepository;
import com.duhyeon.RecordCode.login.service.MemberService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("memberService")
public class MemberServiceImpl implements UserDetailsService,MemberService {

    @Resource(name="emailService")
    private EmailServiceImpl emailService;

    @Resource(name="memberRepository")
    private MemberRepository memberRepository;

    @Override
    public void register(MemberDto memberDto) throws MessagingException {
        if(memberRepository.findByMemberId(memberDto.getMemberId()).isPresent()){
            throw new MessagingException("이미 존재하는 아이디입니다.");
        }

        // 비밀번호 일치 여부 확인
        if(!memberDto.getMemberPw().equals(memberDto.getMemberPwConfirm())){
            throw new MessagingException("비밀번호가 일치하지 않습니다.");
        }

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
        if (!member.isPresent()) {
            throw new UsernameNotFoundException(username + "ID 불일치");
        }
        else{
            Member getMember = member.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add((GrantedAuthority) () -> getMember.getAuth());
            AccountContext accountContext = new AccountContext(getMember, roles);

            return accountContext;
        }
    }


}
