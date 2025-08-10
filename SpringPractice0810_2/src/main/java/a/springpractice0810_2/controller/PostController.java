package a.springpractice0810_2.controller;

import a.springpractice0810_2.dto.PostRequest;
import a.springpractice0810_2.dto.PostResponse;
import a.springpractice0810_2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> savePost(
            @RequestBody PostRequest request
    ){
        return ResponseEntity.ok(postService.save(request));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getposts(){
        return ResponseEntity.ok(postService.findPosts());
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> getPost(
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(postService.findPost(postId));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @RequestBody PostRequest request,
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(postService.updatePost(postId, request));
    }
}
