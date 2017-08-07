package com.example.graphql.location;

import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class LocationDataFetchers {

    @Resource
    private LocationService locationService;

    public DataFetcher<Location> locationDataFetcher() {
        return environment -> {
            String locationId = environment.getArgument("id");
            log.debug("Fetching location {}", locationId);
            return locationService.getLocation(locationId);
        };
    }
}
