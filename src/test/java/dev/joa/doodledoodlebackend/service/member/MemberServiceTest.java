package dev.joa.doodledoodlebackend.service.member;

import dev.joa.doodledoodlebackend.domain.member.Member;
import dev.joa.doodledoodlebackend.dto.member.join.MemberRequestDto;
import dev.joa.doodledoodlebackend.exception.DoodleDoodleException;
import dev.joa.doodledoodlebackend.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("유저 회원 가입")
    @Test
    void joinMember() {
        // given
        given(memberRepository.findByEmail(any()))
                .willReturn(Optional.empty());

        given(memberRepository.save(any()))
                .willReturn(Member.builder()
                                  .id(1L)
                                  .email("aaa@com")
                                  .nickname("건희")
                                  .password("1234")
                                  .build());

        MemberRequestDto memberRequestDto = new MemberRequestDto("aaa@com", "건희", "1234");

        // when
        Member member = memberService.joinMember(memberRequestDto);

        // then
        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getEmail()).isEqualTo(memberRequestDto.getEmail());
        assertThat(member.getNickname()).isEqualTo(memberRequestDto.getNickname());
        assertThat(member.getPassword()).isEqualTo(memberRequestDto.getPassword());

    }

    @DisplayName("유저 회원 가입 시 이미 등록 된 회원이면 회원 가입 불가")
    @Test
    void whenExistSameMemberThenFailJoin() {
        // given
        given(memberRepository.findByEmail(any()))
                .willReturn(Optional.of(Member.builder()
                                              .id(1L)
                                              .email("aaa@com")
                                              .nickname("건희")
                                              .password("1234")
                                              .build()));

        MemberRequestDto memberRequestDto = new MemberRequestDto("aaa@com", "건희", "1234");

        // when
        // then
        assertThrows(DoodleDoodleException.class, () -> memberService.joinMember(memberRequestDto));

    }
}