package dev.joa.doodledoodlebackend.repository.post;

import dev.joa.doodledoodlebackend.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
