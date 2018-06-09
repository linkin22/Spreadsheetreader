package com.ramogi.app.spreadsheetreader;

/**
 * Created by ramogiochola on 11/16/17.
 */

public class RealWinner {

    private String deliveryId,drivername,phonenumber;
    private Double lat, lng, acc;


    public RealWinner(String deliveryId, String drivername, Double lat, Double lng, Double acc)
    {
        this.deliveryId = deliveryId;
        this.drivername = drivername;
        this.lat = lat;
        this.lng = lng;
        this.acc = acc;

    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getAcc() {
        return acc;
    }

    public void setAcc(Double acc) {
        this.acc = acc;
    }
}
