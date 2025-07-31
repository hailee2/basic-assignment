package org.example.springpracticememo.repository;

import org.example.springpracticememo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
