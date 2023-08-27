package com.duhyeon.RecodeCode.login.data.Repository;

import com.duhyeon.RecodeCode.login.data.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
