
package com.mycompany.eventmasterpro;

public class Location {
    private String name;
    private int capacity;
    private String technicalDetails;
    private boolean available;
    private final String city = "Medellín";

    public Location(String name, int capacity, String technicalDetails, boolean available) {
        this.name = name;
        this.capacity = capacity;
        this.technicalDetails = technicalDetails;
        this.available = available;
    }

    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name;
    }

    public int getCapacity() { 
        return capacity; 
    }
    public void setCapacity(int capacity) { 
        this.capacity = capacity;
    }

    public String getTechnicalDetails() { 
        return technicalDetails; 
    }
    public void setTechnicalDetails(String technicalDetails) { 
        this.technicalDetails = technicalDetails; 
    }

    public boolean isAvailable() { 
        return available; 
    }
    public void setAvailable(boolean available) { 
        this.available = available;
    }
    
     public String getCity() { 
         return city; 
     }


    @Override
        public String toString() {
        return "Location: " + name + ", Capacity: " + capacity +
                ", Technical Details: " + technicalDetails +
                ", Available: " + available +
                ", City: " + city;
        }
}