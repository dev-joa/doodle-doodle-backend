package dev.joa.doodledoodlebackend.dto.post.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.joa.doodledoodlebackend.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostCreateResponseDto {

    private Long id;

    private String title;

    private Long price;

    private String description;

    private String location;

    private String picture;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdBy;

    public static PostCreateResponseDto toDto(Post post) {
        return PostCreateResponseDto.builder()
                                    .id(post.getId())
                                    .title(post.getTitle())
                                    .price(post.getPrice())
                                    .description(post.getDescription())
                                    .location(post.getLocation())
                                    .picture(post.getPicture())
                                    .createdBy(post.getCreatedBy())
                                    .build();
    }
}
