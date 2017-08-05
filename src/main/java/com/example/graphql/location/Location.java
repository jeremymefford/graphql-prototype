package com.example.graphql.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Location implements Comparable<Location> {
    private String id;
    private String city;
    private String state;

    @Override
    public int compareTo(Location o) {
        // null values filter to the bottom
        if (o == null) {
            return -1;
        }
        if (o.id == null) {
            return id == null ? 0 : -1;
        }
        if (id == null) {
            return 1;
        }
        return id.compareTo(o.id);
    }
}
