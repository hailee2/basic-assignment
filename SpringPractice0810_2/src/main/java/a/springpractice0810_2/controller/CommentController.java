package a.springpractice0810_2.controller;

import a.springpractice0810_2.dto.CommentRequest;
import a.springpractice0810_2.dto.CommentResponse;
import a.springpractice0810_2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> saveComment(
            @RequestBody CommentRequest request,
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(commentService.save(request, postId));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getAllComments(
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(commentService.findAll(postId));
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> getComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ){
        return ResponseEntity.ok(commentService.findComment(postId, commentId));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest request,
            @PathVariable Long commentId
    ){
        return ResponseEntity.ok(commentService.updateComment(request,postId,commentId));
    }
}

