package com.example.graphql.user;

import com.example.graphql.location.Location;
import com.example.graphql.location.LocationService;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

@Slf4j
@Component
public class UserDataFetchers {

    @Resource
    private UserService userService;

    @Resource
    private LocationService locationService;

    public DataFetcher<User> createUserDataFetcher() {
        return environment -> {
            Map<String, Object> newUser = environment.getArgument("input");
            log.debug("Saving user: {}", newUser);
            return userService.saveUser(new User(
                    "Id is replaced",
                    (String) newUser.get("firstName"),
                    (String) newUser.get("lastName"),
                    Collections.emptyNavigableSet()
            ));
        };
    }

    public DataFetcher<User> userDataFetcher() {
        return environment -> {
            String userId = environment.getArgument("id");
            log.debug("Fetching user {}", userId);
            return userService.getUser(userId);
        };
    }

    public DataFetcher<NavigableSet<Location>> locationDataFetcher() {
        return environment -> {
            String userId = ((User) environment.getSource()).getId();
            log.debug("Fetching locations for user {}", userId);
            return new TreeSet<>(locationService.getLocationsForUser(userId));
        };
    }
}
