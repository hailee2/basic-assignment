package org.example.springpracticememo.service;

import lombok.RequiredArgsConstructor;
import org.example.springpracticememo.dto.MemoRequest;
import org.example.springpracticememo.dto.MemoResponse;
import org.example.springpracticememo.entity.Memo;
import org.example.springpracticememo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponse save(MemoRequest memoRequest){
        Memo memo = new Memo(memoRequest.getContent());
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponse(
                savedMemo.getId(),
                savedMemo.getContent(),
                savedMemo.getCreatedAt(),
                savedMemo.getModifiedAt()
        );
    }

//    @Transactional(readOnly = true)
//    public List<MemoResponse> findMemos(){
//        List<Memo> memos = memoRepository.findAll();
//        List<MemoResponse> dtos = new ArrayList<>();
//
//        for (Memo memo : memos) {
//            MemoResponse memoResponse = new MemoResponse(
//                    memo.getId(),
//                    memo.getContent(),
//                    memo.getCreatedAt(),
//                    memo.getModifiedAt()
//            );
//        }
//        return dtos;
//    }

    @Transactional(readOnly = true)
    public List<MemoResponse> findMemos(){
        List<Memo> memos = memoRepository.findAll();
        return memos.stream()
                .map(memo -> new MemoResponse(
                        memo.getId(),
                        memo.getContent(),
                        memo.getCreatedAt(),
                        memo.getModifiedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public MemoResponse findMemo(Long memoId){
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                ()-> new IllegalArgumentException("해당하는 MEMO가 존재하지 않습니다.")
        );
        return new MemoResponse(
                memo.getId(),
                memo.getContent(),
                memo.getCreatedAt(),
                memo.getModifiedAt()
        );
    }

    @Transactional
    public MemoResponse updateMemoContent(Long memoId, MemoRequest memoRequest){
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 MEMO입니다.")
        );
        memo.updateMemoContent(memoRequest.getContent());
        return new MemoResponse(
                memo.getId(),
                memo.getContent(),
                memo.getCreatedAt(),
                memo.getModifiedAt()
        );
    }

    @Transactional
    public void deleteMemo(Long memoId){
        boolean b = memoRepository.existsById(memoId);
        if(!b) {
            throw new IllegalArgumentException("존재하지 않는 MEMO");
        }
        memoRepository.deleteById(memoId);
    }
}
