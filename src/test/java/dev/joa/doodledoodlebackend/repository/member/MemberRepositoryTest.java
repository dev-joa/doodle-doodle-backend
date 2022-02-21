package dev.joa.doodledoodlebackend.repository.member;

import dev.joa.doodledoodlebackend.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원 가입 쿼리 검증")
    @Test
    void joinMember() {
        // given
        Member member = Member.builder()
                              .email("aaa@com")
                              .nickname("건희")
                              .password("!Ssss0000")
                              .build();

        // when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(findMember.getNickname()).isEqualTo(member.getNickname());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
    }
}