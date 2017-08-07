package com.example.graphql.location;

import com.example.graphql.util.CollectionsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Resource
    private LocationEntityRepository repo;

    private static Location fromEntity(LocationEntity entity) {
        return new Location(
                entity.getId(),
                entity.getCity(),
                entity.getState());
    }

    public Location getLocation(String locationId) {
        return fromEntity(repo.findOne(locationId));
    }

    public List<Location> getLocationsForUser(String userId) {
        return CollectionsUtil.nonNullStream(repo.locationsForUser(userId))
                .map(LocationService::fromEntity)
                .collect(Collectors.toList());
    }
}
