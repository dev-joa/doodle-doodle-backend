package dev.joa.doodledoodlebackend.service.member;

import dev.joa.doodledoodlebackend.domain.member.Member;
import dev.joa.doodledoodlebackend.dto.member.MemberRequestDto;
import dev.joa.doodledoodlebackend.exception.DoodleDoodleException;
import dev.joa.doodledoodlebackend.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member joinMember(MemberRequestDto memberDto) {
        Member member = memberDto.toMember();
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        if (findMember.isPresent()) {
            throw new DoodleDoodleException("이미 존재하는 회원입니다.");
        }

        return memberRepository.save(member);
    }
}
