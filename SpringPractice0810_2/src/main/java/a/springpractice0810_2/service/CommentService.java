package a.springpractice0810_2.service;

import a.springpractice0810_2.dto.CommentRequest;
import a.springpractice0810_2.dto.CommentResponse;
import a.springpractice0810_2.entity.Comment;
import a.springpractice0810_2.entity.Post;
import a.springpractice0810_2.repository.CommentRepository;
import a.springpractice0810_2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponse save(CommentRequest request, Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );
        Comment comment = new Comment(
                post,
                request.getComment()
        );
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponse(
                savedComment.getId(),
                savedComment.getComment(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findAll(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );
        List<Comment> posts = commentRepository.findAllByPost(post);
        List<CommentResponse> dtos = new ArrayList<>();

        for (Comment comment : posts) {
            dtos.add(
                    new CommentResponse(
                            comment.getId(),
                            comment.getComment(),
                            comment.getCreatedAt(),
                            comment.getModifiedAt()
                    )
            );
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public CommentResponse findComment(Long postId, Long commentId){
        postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 리뷰입니다.")
        );
        if (!comment.getPost().getId().equals(postId)){
            throw new IllegalArgumentException("해당 포스트에 대한 코멘트가 아닙니다.");
        }

        return new CommentResponse(
                comment.getId(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    @Transactional
    public CommentResponse updateComment(CommentRequest request, Long postId, Long commentId){
        postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 리뷰입니다.")
        );
        if (!comment.getPost().getId().equals(postId)){
            throw new IllegalArgumentException("해당 포스트에 대한 코멘트가 아닙니다.");
        }
        comment.updateComment(request.getComment());
        return new CommentResponse(
                comment.getId(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
