package com.ringencorp.ezrtt.backend.model;

/**
 * Created by dcw3gh on 1/19/2016.
 */
public class LatLng {
    private float lat;
    private float lng;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public LatLng(float lat, float lng)
    {
        this.lat = lat;
        this.lng = lng;
    }
}
