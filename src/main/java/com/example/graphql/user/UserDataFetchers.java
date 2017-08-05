package com.example.graphql.user;

import com.example.graphql.location.Location;
import com.example.graphql.location.LocationDataFetchers;
import com.example.graphql.location.LocationEntity;
import com.example.graphql.util.CollectionsUtil;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserDataFetchers {
    @Resource
    private EntityManager em;

    public static User fromEntity(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                Collections.emptyNavigableSet());
    }

    public DataFetcher<User> userDataFetcher() {
        return environment -> {
            val userId = environment.getArgument("id");
            log.debug("Fetching user {}", userId);
            UserEntity entity = em.createQuery("from UserEntity where id = :id", UserEntity.class)
                    .setParameter("id", userId)
                    .getSingleResult();
            return fromEntity(entity);
        };
    }

    public DataFetcher<NavigableSet<Location>> locationDataFetcher() {
        return environment -> {
            val userId = ((User) environment.getSource()).getId();
            log.debug("Fetching locations for user {}", userId);
            return lookupLocationsByUser(userId);
        };
    }

    private NavigableSet<Location> lookupLocationsByUser(String userId) {
        List<LocationEntity> userLocations = (List<LocationEntity>) em.createNativeQuery("SELECT l.* FROM LOCATIONS l INNER JOIN USER_HAS_LOCATIONS u_l ON u_l.location_id = l.id WHERE u_l.user_id = ?1", LocationEntity.class)
                .setParameter(1, userId)
                .getResultList();
        return CollectionsUtil.nonNullStream(userLocations)
                .map(LocationDataFetchers::fromEntity)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
