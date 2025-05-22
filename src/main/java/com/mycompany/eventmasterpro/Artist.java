
package com.mycompany.eventmasterpro;

public class Artist {
    private String name;
    private String contactInfo;
    private String technicalRequirements;

    public Artist(String name, String contactInfo, String technicalRequirements) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.technicalRequirements = technicalRequirements;
    }

    @Override
    public String toString() {
        return "Artist: " + name + ", Contact: " + contactInfo + ", Requirements: " + technicalRequirements;
    }
}
