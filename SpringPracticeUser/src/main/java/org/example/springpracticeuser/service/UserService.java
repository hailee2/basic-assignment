package org.example.springpracticeuser.service;

import lombok.RequiredArgsConstructor;
import org.example.springpracticeuser.dto.UserRequest;
import org.example.springpracticeuser.dto.UserResponse;
import org.example.springpracticeuser.entity.User;
import org.example.springpracticeuser.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse save(UserRequest userRequest) {
        User user = new User(userRequest.getName());
        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

//    @Transactional(readOnly = true)
//    public List<UserResponse> findUsers() {
//        List<User> users = userRepository.findAll();
//        return users.stream()
//                .map(user -> new UserResponse(
//                        user.getId(),
//                        user.getName(),
//                        user.getCreatedAt(),
//                        user.getModifiedAt()
//                ))
//                .toList();
//    }

    @Transactional(readOnly = true)
    public List<UserResponse> findUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> dtos = new ArrayList<>();

        for (User user : users) {
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtos.add(userResponse);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public UserResponse findUser(Long userId){

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memberID가 없습니다.")
        );
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UserResponse updateUserName(Long userId, UserRequest userRequest){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memberID가 없습니다.")
        );
        user.updateUserName(userRequest.getName());
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public void deleteUser (Long userId){
        boolean b = userRepository.existsById(userId);
        if(!b) {
            throw new IllegalArgumentException("해당하는 memberID가 없습니다.");
        }
        userRepository.deleteById(userId);
    }

}
