package com.model;

import javax.persistence.*;

@Entity
@Table(name = "point")
public class Point{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "description")
    private String description;

    protected Point() {
    }

    public Point (String coordinates, String description) {
        this.coordinates = coordinates;
        this.description = description;
    }

    public long getId() { return id; }

    @Override
    public String toString() {
        return String.format("Point[id=%d, coordinates='%s', description='%s']", id, coordinates, description);
    }
}
