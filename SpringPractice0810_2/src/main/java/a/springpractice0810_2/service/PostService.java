package a.springpractice0810_2.service;

import a.springpractice0810_2.dto.PostRequest;
import a.springpractice0810_2.dto.PostResponse;
import a.springpractice0810_2.entity.Post;
import a.springpractice0810_2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponse save(PostRequest request) {
        Post post = new Post(request.getTitle(), request.getContent());
        Post savedPost = postRepository.save(post);
        return new PostResponse(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getCreatedAt(),
                savedPost.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt(),
                        post.getModifiedAt()
                )).toList();
    }

    @Transactional(readOnly = true)
    public PostResponse findPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getModifiedAt()
        );
    }

    @Transactional
    public PostResponse updatePost(Long postId, PostRequest request){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );
        post.updatePost(request.getTitle(),request.getContent());
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getModifiedAt()
        );
    }
}
