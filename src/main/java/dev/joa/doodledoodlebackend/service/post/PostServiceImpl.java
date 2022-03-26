package dev.joa.doodledoodlebackend.service.post;

import dev.joa.doodledoodlebackend.domain.post.Post;
import dev.joa.doodledoodlebackend.dto.post.create.PostCreateRequestDto;
import dev.joa.doodledoodlebackend.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(PostCreateRequestDto postCreateRequestDto) {
        return postRepository.save(postCreateRequestDto.toPost());
    }
}
