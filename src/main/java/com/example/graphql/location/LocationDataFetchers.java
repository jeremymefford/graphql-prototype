package com.example.graphql.location;

import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

@Slf4j
@Component
public class LocationDataFetchers {

    @Resource
    private EntityManager em;

    public static Location fromEntity(LocationEntity entity) {
        return new Location(
                entity.getId(),
                entity.getCity(),
                entity.getState());
    }

    public DataFetcher<Location> locationDataFetcher() {
        return environment -> {
            val locationId = environment.getArgument("id");
            log.debug("Fetching location {}", locationId);
            LocationEntity entity = em.createQuery("from LocationEntity where id = :id", LocationEntity.class)
                    .setParameter("id", locationId)
                    .getSingleResult();
            return fromEntity(entity);
        };
    }
}
