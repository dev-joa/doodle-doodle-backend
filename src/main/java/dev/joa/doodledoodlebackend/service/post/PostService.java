package dev.joa.doodledoodlebackend.service.post;

import dev.joa.doodledoodlebackend.domain.post.Post;
import dev.joa.doodledoodlebackend.dto.post.create.PostCreateRequestDto;

import java.util.List;

public interface PostService {

    List<Post> getPosts();

    Post createPost(PostCreateRequestDto postCreateRequestDto);
}
