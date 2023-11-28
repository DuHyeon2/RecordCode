package com.duhyeon.RecodeCode.login.data.Repository;

import com.duhyeon.RecodeCode.login.data.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String username);
}
