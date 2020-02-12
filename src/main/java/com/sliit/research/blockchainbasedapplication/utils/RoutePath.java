package com.sliit.research.blockchainbasedapplication.utils;

import java.util.List;

public class RoutePath {
    private List<RouteElement> path;
    private int distance;

    public List<RouteElement> getPath() {
        return path;
    }

    public void setPath(List<RouteElement> path) {
        this.path = path;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
