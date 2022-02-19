package dev.joa.doodledoodlebackend.dto.member;

import dev.joa.doodledoodlebackend.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long id;
    private String email;
    private String nickname;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
