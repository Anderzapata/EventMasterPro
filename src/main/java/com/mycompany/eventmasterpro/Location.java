
package com.mycompany.eventmasterpro;

public class Location {
    private String name;
    private int capacity;
    private String technicalSpecs;

    public Location(String name, int capacity, String technicalSpecs) {
        this.name = name;
        this.capacity = capacity;
        this.technicalSpecs = technicalSpecs;
    }

    @Override
    public String toString() {
        return "Location: " + name + ", Capacity: " + capacity + ", Specs: " + technicalSpecs;
    }
}