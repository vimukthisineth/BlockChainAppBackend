package com.sliit.research.blockchainbasedapplication.utils;

public class RouteElement {
    private int distance;
    private double durationInSeconds;
    private String duration;
    private String destination;

    public RouteElement() {
    }

    public RouteElement(int distance, double durationInSeconds, String duration, String destination) {
        this.distance = distance;
        this.durationInSeconds = durationInSeconds;
        this.duration = duration;
        this.destination = destination;
    }

    public RouteElement(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(double durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
