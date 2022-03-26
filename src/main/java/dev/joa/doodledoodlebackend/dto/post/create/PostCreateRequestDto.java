package dev.joa.doodledoodlebackend.dto.post.create;

import dev.joa.doodledoodlebackend.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class PostCreateRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @Min(1)
    private Long price;

    private String description;

    private String location;

    private String picture;

    public Post toPost() {
        return Post.builder()
                   .title(title)
                   .price(price)
                   .description(description)
                   .location(location)
                   .picture(picture)
                   .build();
    }
}
