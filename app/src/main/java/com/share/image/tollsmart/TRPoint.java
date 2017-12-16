package com.share.image.tollsmart;

public class TRPoint {
    Double latitude = null;
    Double longitude = null;    
    Double speed = null;//Recommended for best results. Serves as a predicate for choosing points   
    Double signal_accuracy = 5.0;

    public TRPoint() {
        
    }
    public TRPoint(Double lat, Double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }
     
    public TRPoint(Double lat, Double lon, Double sp) {
        this.latitude = lat;
        this.longitude = lon;
        this.speed = sp;
    }
    
    
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getSignal_accuracy() {
        return signal_accuracy;
    }

    public void setSignal_accuracy(Double signal_accuracy) {
        this.signal_accuracy = signal_accuracy;
    }
    
    
}
