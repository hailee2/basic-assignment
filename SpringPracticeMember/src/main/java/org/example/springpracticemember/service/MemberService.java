package org.example.springpracticemember.service;

import lombok.RequiredArgsConstructor;
import org.example.springpracticemember.dto.MemberRequest;
import org.example.springpracticemember.dto.MemberResponse;
import org.example.springpracticemember.entity.Member;
import org.example.springpracticemember.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse save(MemberRequest memberRequest){
        Member member = new Member(memberRequest.getName());
        Member savedMember = memberRepository.save(member);

        return new MemberResponse(
                savedMember.getId(),
                savedMember.getName(),
                savedMember.getCreatedAt(),
                savedMember.getModifiedAt()
        );
    }

//    @Transactional(readOnly = true)
//    public List<MemberResponse> findMembers(){
//        List<Member> members = memberRepository.findAll();
//        return members.stream()
//                .map(member -> new MemberResponse(
//                        member.getId(),
//                        member.getName(),
//                        member.getCreatedAt(),
//                        member.getModifiedAt()
//                ))
//                .toList();
//    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findMembers(){
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> dtos = new ArrayList<>();

        for (Member member : members) {
            MemberResponse memberResponse = new MemberResponse(
                    member.getId(),
                    member.getName(),
                    member.getCreatedAt(),
                    member.getModifiedAt()
            );
            dtos.add(memberResponse);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemberResponse findMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 memberID입니다.")
        );
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );
    }

    @Transactional
    public MemberResponse updateMemberName(Long memberId, MemberRequest memberRequest){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 memberID입니다.")
        );
        member.updateMemberName(memberRequest.getName());
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );
    }

    @Transactional
    public void deleteMember(Long memberId){
        boolean b = memberRepository.existsById(memberId);
        if(!b){
            throw new IllegalArgumentException("존재하지 않는 memberID임");
        }
        memberRepository.deleteById(memberId);
    }
}
