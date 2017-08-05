package com.example.graphql.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOCATIONS")
public class LocationEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;
}
