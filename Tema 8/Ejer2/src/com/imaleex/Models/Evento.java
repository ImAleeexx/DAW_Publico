package com.imaleex.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Alex Cortes
 */
public class Evento {
    private int id;
    private String name;
    private String location;
    private int maxAssistants;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;

    public Evento(String name, String location, int maxAssistants, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.location = location;
        this.maxAssistants = maxAssistants;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Evento(int id, String name, String location, int maxAssistants, LocalDateTime startTime, LocalDateTime endTime, Boolean active) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.maxAssistants = maxAssistants;
        this.startTime = startTime;
        this.endTime = endTime;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxAssistants() {
        return maxAssistants;
    }

    public void setMaxAssistants(int maxAssistants) {
        this.maxAssistants = maxAssistants;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
