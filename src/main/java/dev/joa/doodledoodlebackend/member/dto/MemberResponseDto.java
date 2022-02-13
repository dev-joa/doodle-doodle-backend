package dev.joa.doodledoodlebackend.member.dto;

import dev.joa.doodledoodlebackend.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private String email;
    private String nickname;

    public MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
