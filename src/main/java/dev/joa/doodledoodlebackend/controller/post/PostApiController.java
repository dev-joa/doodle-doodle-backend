package dev.joa.doodledoodlebackend.controller.post;

import dev.joa.doodledoodlebackend.domain.common.BaseResponse;
import dev.joa.doodledoodlebackend.dto.post.create.PostCreateRequestDto;
import dev.joa.doodledoodlebackend.dto.post.create.PostCreateResponseDto;
import dev.joa.doodledoodlebackend.dto.post.home.PostResponseDto;
import dev.joa.doodledoodlebackend.service.post.PostService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "게시글")
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostApiController {

    private final PostService postService;

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    @Operation(summary = "게시글 리스트 조회", description = "메인 페이지 게시글 리스트 조회")
    @GetMapping
    public BaseResponse<List<PostResponseDto>> getPosts() {
        return new BaseResponse<>(PostResponseDto.toDtos(postService.getPosts()));
    }

    /**
     * 게시글 등록
     * @param postCreateRequestDto 게시글 정보
     * @return 게시글 등록 성공
     */
    @Operation(summary = "게시글 등록")
    @PostMapping
    public BaseResponse<PostCreateResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto postCreateRequestDto) {
        return new BaseResponse<>(PostCreateResponseDto.toDto(postService.createPost(postCreateRequestDto)));
    }
}
