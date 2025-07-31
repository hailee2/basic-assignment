package org.example.springpracticeuser.repository;

import org.example.springpracticeuser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
