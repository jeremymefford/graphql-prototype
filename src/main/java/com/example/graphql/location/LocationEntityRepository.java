package com.example.graphql.location;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationEntityRepository extends CrudRepository<LocationEntity, String> {
    @Query(value = "SELECT l.* FROM LOCATIONS l INNER JOIN USER_HAS_LOCATIONS u_l ON u_l.location_id = l.id WHERE u_l.user_id = ?1", nativeQuery = true)
    List<LocationEntity> locationsForUser(String userId);
}
