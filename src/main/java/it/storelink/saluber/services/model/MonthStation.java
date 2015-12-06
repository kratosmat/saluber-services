package it.storelink.saluber.services.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.05
 */
@Entity
@DiscriminatorValue("STATION")
public class MonthStation extends Month {

    private Long stationId;

    @Basic
    @Column(name = "STATION_ID", nullable = true, insertable = true, updatable = true)
    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

}
