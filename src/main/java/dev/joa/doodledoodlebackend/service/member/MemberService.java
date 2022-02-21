package dev.joa.doodledoodlebackend.service.member;

import dev.joa.doodledoodlebackend.domain.member.Member;
import dev.joa.doodledoodlebackend.dto.member.join.MemberRequestDto;

public interface MemberService {

    /**
     * 유저 회원 가입
     *
     * @param member 유저 회원 가입 정보
     * @return 회원 가입 한 유저
     */
    Member joinMember(MemberRequestDto member);
}
