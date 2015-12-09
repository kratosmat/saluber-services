package it.storelink.saluber.services.vo;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.36
 */

public class StationVO {

    private Long _id;
    private String _name;
    private String _completeAddress;
    private String photo;
    private Double _lat;
    private Double _lon;
    private Integer _nRoom;

    public Long getId() {
        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getCompleteAddress() {
        return _completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this._completeAddress = completeAddress;
    }

    public Double getLat() {
        return _lat;
    }

    public void setLat(Double lat) {
        this._lat = lat;
    }

    public Double getLon() {
        return _lon;
    }

    public void setLon(Double lon) {
        this._lon = lon;
    }

    public Integer getNRoom() {
        return _nRoom;
    }

    public void setNRoom(Integer nRoom) {
        this._nRoom = nRoom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
