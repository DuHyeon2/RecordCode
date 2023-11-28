package com.duhyeon.RecodeCode.security;

import com.duhyeon.RecodeCode.login.service.impl.MemberServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "memberService")
    private MemberServiceImpl memberServiceImpl;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String memberId = authentication.getName();
        String memberPw = (String) authentication.getCredentials();

        UserDetails member = memberServiceImpl.loadUserByUsername(memberId);

        if(!passwordEncoder.matches(memberPw, member.getPassword())){
            System.out.println("CustomAuthenticationProvider 실패");
            throw new AuthenticationException("비밀번호가 일치하지 않습니다.") {
            };
        }
        System.out.println("CustomAuthenticationProvider 성공");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member, memberPw, member.getAuthorities());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
