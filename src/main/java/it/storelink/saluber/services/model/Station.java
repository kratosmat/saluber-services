package it.storelink.saluber.services.model;

import javax.persistence.*;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.36
 */

@Entity
@Table(name = "STATION", catalog = "")
public class Station {

    private Long _id;
    private String _name;
    private String _completeAddress;
    private Double _lat;
    private Double _lon;
    private Integer _nRoom;

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGen")
    //@SequenceGenerator(name = "SeqGen", sequenceName = "SEQ_PATIENT", allocationSize = 1)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = -127)
    public Long getId() {
        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = _name;
    }

    @Basic
    @Column(name = "ADDRESS", nullable = true, insertable = true, updatable = true, length = 500)
    public String getCompleteAddress() {
        return _completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this._completeAddress = completeAddress;
    }

    @Basic
    @Column(name = "LAT", nullable = true, insertable = true, updatable = true)
    public Double getLat() {
        return _lat;
    }

    public void setLat(Double lat) {
        this._lat = lat;
    }

    @Basic
    @Column(name = "LON", nullable = true, insertable = true, updatable = true)
    public Double getLon() {
        return _lon;
    }

    public void setLon(Double lon) {
        this._lon = lon;
    }

    @Basic
    @Column(name = "NROOM", nullable = true, insertable = true, updatable = true)
    public Integer getNRoom() {
        return _nRoom;
    }

    public void setNRoom(Integer nRoom) {
        this._nRoom = nRoom;
    }

}
