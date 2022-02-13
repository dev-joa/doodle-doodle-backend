package dev.joa.doodledoodlebackend.member.service;

import dev.joa.doodledoodlebackend.member.domain.Member;
import dev.joa.doodledoodlebackend.member.dto.MemberRequestDto;

public interface MemberService {

    /**
     * 유저 회원 가입
     *
     * @param member 유저 회원 가입 정보
     * @return 회원 가입 한 유저
     */
    Member joinMember(MemberRequestDto member);
}
