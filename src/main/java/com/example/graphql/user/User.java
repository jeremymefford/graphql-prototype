package com.example.graphql.user;

import com.example.graphql.location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.NavigableSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private NavigableSet<Location> locations;
}
