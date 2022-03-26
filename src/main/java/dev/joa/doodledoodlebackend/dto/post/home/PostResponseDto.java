package dev.joa.doodledoodlebackend.dto.post.home;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.joa.doodledoodlebackend.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PostResponseDto {

    private String title;

    private String description;

    private Long price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdBy;

    public static PostResponseDto toDto(Post post) {
        return PostResponseDto.builder()
                              .title(post.getTitle())
                              .description(post.getDescription())
                              .price(post.getPrice())
                              .createdBy(post.getCreatedBy())
                              .build();
    }

    public static List<PostResponseDto> toDtos(List<Post> posts) {
        return posts.stream()
                    .map(PostResponseDto::toDto)
                    .collect(Collectors.toList());
    }
}
