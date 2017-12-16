package com.share.image.tollsmart;

/**
 * Created by test on 5/12/17.
 */

import java.util.ArrayList;

/**
 *
 * @author leonid
 */
public class TollPostRequest {
    String origin;
    String destination;
    ArrayList<String>waypoints;
    boolean use_express_lanes = true;
    boolean include_unavailable_express_lanes = true;
    String map_provider;
    ArrayList<String>usaccounts;
    ArrayList<String>canadaAccounts;
    ArrayList<GeoPoint>supplied_route;
    ArrayList<TRPoint> trip_array;
    Vehicle vehicle;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setMap_provider(String map_provider) {
        this.map_provider = map_provider;
    }

    public void setUsaccounts(ArrayList<String> usaccounts) {
        this.usaccounts = usaccounts;
    }

    public void setCanadaAccounts(ArrayList<String> canadaAccounts) {
        this.canadaAccounts = canadaAccounts;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ArrayList<GeoPoint> getSupplied_route() {
        return supplied_route;
    }

    public void setSupplied_route(ArrayList<GeoPoint> supplied_route) {
        this.supplied_route = supplied_route;
    }

    public ArrayList<TRPoint> getTrip_array() {
        return trip_array;
    }

    public void setTrip_array(ArrayList<TRPoint> trip_array) {
        this.trip_array = trip_array;
    }

    public ArrayList<String> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(ArrayList<String> waypoints) {
        this.waypoints = waypoints;
    }

    public boolean isUse_express_lanes() {
        return use_express_lanes;
    }

    public void setUse_express_lanes(boolean use_express_lanes) {
        this.use_express_lanes = use_express_lanes;
    }

    public boolean isInclude_unavailable_express_lanes() {
        return include_unavailable_express_lanes;
    }

    public void setInclude_unavailable_express_lanes(boolean include_unavailable_express_lanes) {
        this.include_unavailable_express_lanes = include_unavailable_express_lanes;
    }



}


