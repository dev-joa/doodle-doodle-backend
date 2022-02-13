package dev.joa.doodledoodlebackend.member.controller;

import dev.joa.doodledoodlebackend.common.api.BaseResponse;
import dev.joa.doodledoodlebackend.member.domain.Member;
import dev.joa.doodledoodlebackend.member.dto.MemberRequestDto;
import dev.joa.doodledoodlebackend.member.dto.MemberResponseDto;
import dev.joa.doodledoodlebackend.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "유저")
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 유저 회원 가입
     *
     * @param member 회원 가입 유저 정보
     * @return 회원 정보
     */
    @Operation(summary = "유저 회원 가입", description = "회원 가입")
    @PostMapping
    public BaseResponse joinMember(@Valid @RequestBody MemberRequestDto member) {
        Member joinedMember = memberService.joinMember(member);
        return new BaseResponse<>(new MemberResponseDto(joinedMember));
    }

}
