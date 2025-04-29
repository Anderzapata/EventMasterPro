
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

    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }

    public String getContactInfo() { 
        return contactInfo; 
    }
    public void setContactInfo(String contactInfo) { 
        this.contactInfo = contactInfo;
    }

    public String getTechnicalRequirements() { 
        return technicalRequirements; 
    }
    public void setTechnicalRequirements(String technicalRequirements) { 
        this.technicalRequirements = technicalRequirements; 
    }

    @Override
    public String toString() {
        return "Artist: " + name + ", Contact: " + contactInfo + ", Requirements: " + technicalRequirements;
    }
}
