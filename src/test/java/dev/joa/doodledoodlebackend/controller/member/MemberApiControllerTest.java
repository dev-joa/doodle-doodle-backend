package dev.joa.doodledoodlebackend.controller.member;

import dev.joa.doodledoodlebackend.domain.member.Member;
import dev.joa.doodledoodlebackend.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(MemberApiController.class)
class MemberApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    @DisplayName("필수값을 만족한 유저는 회원 가입이 가능하다")
    @Test
    void whenVerifiedMemberThenSuccessJoin() throws Exception {
        // given
        given(memberService.joinMember(any()))
                .willReturn(
                        Member.builder()
                              .id(1L)
                              .email("abc@com")
                              .nickname("건희")
                              .password("!A123456")
                              .build());

        String json = "{\n" +
                "    \"email\": \"abc@com\",\n" +
                "    \"nickname\": \"건희\",\n" +
                "    \"password\": \"!A123456\"\n" +
                "}";

        // when
        ResultActions actions = postJoinMember(json);

        // then
        actions.andExpect(status().isOk())
               .andDo(print());
    }

    @DisplayName("이메일 형식을 만족하지 않은 유저는 회원 가입이 불가능하다")
    @Test
    void whenNotVerifiedEmailThenFailJoin() throws Exception {
        // given
        // @ 뒤가 없는 경우
        String json1 = "{\n" +
                "    \"email\": \"abc@\",\n" +
                "    \"nickname\": \"건희\",\n" +
                "    \"password\": \"!A123456\"\n" +
                "}";

        // @ 가 없는 경우
        String json2 = "{\n" +
                "    \"email\": \"abccom\",\n" +
                "    \"nickname\": \"건희\",\n" +
                "    \"password\": \"!A123456\"\n" +
                "}";

        // 빈값
        String json3 = "{\n" +
                "    \"email\": \"\",\n" +
                "    \"nickname\": \"건희\",\n" +
                "    \"password\": \"!A123456\"\n" +
                "}";

        // when
        ResultActions actions1 = postJoinMember(json1);
        ResultActions actions2 = postJoinMember(json2);
        ResultActions actions3 = postJoinMember(json3);

        // then
        actions1.andExpect(status().isBadRequest())
                .andDo(print());

        actions2.andExpect(status().isBadRequest())
                .andDo(print());

        actions3.andExpect(status().isBadRequest())
                .andDo(print());
    }

    @DisplayName("닉네임 형식을 만족하지 않은 유저는 회원 가입이 불가능하다")
    @Test
    void whenNotVerifiedNicknameThenFailJoin() throws Exception {
        // given
        // 닉네임은 1 ~ 7자 이여야 한다.

        // 빈값
        String json1 = "{\n" +
                "    \"email\": \"abc@com\",\n" +
                "    \"nickname\": \"\",\n" +
                "    \"password\": \"!A123456\"\n" +
                "}";

        // 8자 이상
        String json2 = "{\n" +
                "    \"email\": \"abc@com\",\n" +
                "    \"nickname\": \"건희건희건희건건\",\n" +
                "    \"password\": \"!A123456\"\n" +
                "}";

        // when
        ResultActions actions1 = postJoinMember(json1);
        ResultActions actions2 = postJoinMember(json2);

        // then
        actions1.andExpect(status().isBadRequest())
                .andDo(print());

        actions2.andExpect(status().isBadRequest())
                .andDo(print());
    }

    @DisplayName("비밀번호 형식을 만족하지 않은 유저는 회원 가입이 불가능하다")
    @Test
    void whenNotVerifiedPasswordThenFailJoin() throws Exception {
        // given
        // 비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호이어야 한다

        // 숫자
        String json1 = "{\n" +
                "    \"email\": \"abc@com\",\n" +
                "    \"nickname\": \"건희\",\n" +
                "    \"password\": \"1234567\"\n" +
                "}";

        // 숫자 + 특수문자
        String json2 = "{\n" +
                "    \"email\": \"abc@com\",\n" +
                "    \"nickname\": \"건희\",\n" +
                "    \"password\": \"!1234567\"\n" +
                "}";

        // 특수문자
        String json3 = "{\n" +
                "    \"email\": \"abc@com\",\n" +
                "    \"nickname\": \"건희\",\n" +
                "    \"password\": \"!!!!!!!!\"\n" +
                "}";

        // 영문
        String json4 = "{\n" +
                "    \"email\": \"abc@com\",\n" +
                "    \"nickname\": \"건희\",\n" +
                "    \"password\": \"ssssssss\"\n" +
                "}";

        // 영문 + 숫자
        String json5 = "{\n" +
                "    \"email\": \"abc@com\",\n" +
                "    \"nickname\": \"건희\",\n" +
                "    \"password\": \"ssss1111\"\n" +
                "}";

        // when
        ResultActions actions1 = postJoinMember(json1);
        ResultActions actions2 = postJoinMember(json2);
        ResultActions actions3 = postJoinMember(json3);
        ResultActions actions4 = postJoinMember(json4);
        ResultActions actions5 = postJoinMember(json5);

        // then
        actions1.andExpect(status().isBadRequest());
        actions2.andExpect(status().isBadRequest());
        actions3.andExpect(status().isBadRequest());
        actions4.andExpect(status().isBadRequest());
        actions5.andExpect(status().isBadRequest());
    }

    private ResultActions postJoinMember(String json) throws Exception {
        return mvc.perform(
                post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));
    }
}