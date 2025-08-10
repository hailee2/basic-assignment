package a.springpractice0810_2.repository;

import a.springpractice0810_2.entity.Comment;
import a.springpractice0810_2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
