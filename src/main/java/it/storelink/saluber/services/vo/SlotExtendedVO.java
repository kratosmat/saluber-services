package it.storelink.saluber.services.vo;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.03
 */

public class SlotExtendedVO extends SlotBaseVO {

    private Long doctorId;
    private Long stationId;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    private Boolean doctorAvailability;
    private Boolean stationAvailability;

    public Boolean getDoctorAvailability() {
        return doctorAvailability;
    }

    public void setDoctorAvailability(Boolean doctorAvailability) {
        this.doctorAvailability = doctorAvailability;
    }

    public Boolean getStationAvailability() {
        return stationAvailability;
    }

    public void setStationAvailability(Boolean stationAvailability) {
        this.stationAvailability = stationAvailability;
    }
}
