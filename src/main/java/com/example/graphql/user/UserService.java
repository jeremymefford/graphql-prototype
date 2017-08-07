package com.example.graphql.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

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

    public User getUser(String userId) {
        return fromEntity(repo.findOne(userId));
    }

}
