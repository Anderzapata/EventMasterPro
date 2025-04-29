
package com.mycompany.eventmasterpro;


public class Event {
   private String name;
    private String date;
    private String time;
    private Location location;
    private EventType eventType;

    public Event(String name, String date, String time, Location location, EventType eventType) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.eventType = eventType;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public EventType getEventType() { return eventType; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }

    @Override
    public String toString() {
        return "Event: " + name + ", Date: " + date + ", Time: " + time + ", Location: " +
                location.getName() + ", Type: " + eventType;
    }
}