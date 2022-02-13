package dev.joa.doodledoodlebackend.member.dto;

import dev.joa.doodledoodlebackend.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String email;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(min = 1, max = 7, message = "닉네임은 1 ~ 7자 이여야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호이어야 합니다.")
    private String password;

    public Member toMember() {
        return Member.builder()
                     .email(email)
                     .nickname(nickname)
                     .password(password)
                     .build();
    }
}
