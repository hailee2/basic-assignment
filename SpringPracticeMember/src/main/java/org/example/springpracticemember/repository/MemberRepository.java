package org.example.springpracticemember.repository;

import org.example.springpracticemember.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
