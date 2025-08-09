package org.example.springpracticememo.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpracticememo.dto.MemoRequest;
import org.example.springpracticememo.dto.MemoResponse;
import org.example.springpracticememo.service.MemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoResponse> createMemo(
            @RequestBody MemoRequest memorequest
    ){
        return ResponseEntity.ok(memoService.save(memorequest));
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponse>> getMemos() {
        return ResponseEntity.ok(memoService.findMemos());
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponse> getMemo(
            @PathVariable Long memoId
    ){
        return ResponseEntity.ok(memoService.findMemo(memoId));
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponse> updateMemoContent(
            @PathVariable Long memoId,
            @RequestBody MemoRequest memoRequest
    ){
        return ResponseEntity.ok(memoService.updateMemoContent(memoId, memoRequest));
    }

    @DeleteMapping("/memos/{memoId}")
    public void deleteMemo(
            @PathVariable Long memoId
    ){
        memoService.deleteMemo(memoId);
    }
}
