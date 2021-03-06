package com.example.graphql.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserService {

    @Resource
    private UserEntityRepository repo;

    private static User fromEntity(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                Collections.emptyNavigableSet());
    }

    private static UserEntity fromUser(User user) {
        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    public User saveUser(User user) {
        UserEntity entity = fromUser(user);
        entity.setId(UUID.randomUUID().toString());
        return fromEntity(repo.save(entity));
    }

    public User getUser(String userId) {
        return fromEntity(repo.findOne(userId));
    }

}
