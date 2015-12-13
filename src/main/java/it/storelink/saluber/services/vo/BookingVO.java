package it.storelink.saluber.services.vo;

import java.sql.Timestamp;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.38
 */

public class BookingVO {

    private Long _id;
    private Timestamp _dateStart;
    private Long _stationId;
    private Long _doctorId;
    private Long _patientId;
    private Long _specializationId;
    private Long doctorSlot;
    private Long stationSlot;


    public Long getId() {
        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    public Timestamp getDateStart() {
        return _dateStart;
    }

    public void setDateStart(Timestamp _dateStart) {
        this._dateStart = _dateStart;
    }

    public Long getStationId() {
        return _stationId;
    }

    public void setStationId(Long _stationId) {
        this._stationId = _stationId;
    }

    public Long getDoctorId() {
        return _doctorId;
    }

    public void setDoctorId(Long _doctorId) {
        this._doctorId = _doctorId;
    }

    public Long getPatientId() {
        return _patientId;
    }

    public void setPatientId(Long _patientId) {
        this._patientId = _patientId;
    }

    public Long getSpecializationId() {
        return _specializationId;
    }

    public void setSpecializationId(Long _specializationId) {
        this._specializationId = _specializationId;
    }

    public Long getDoctorSlot() {
        return doctorSlot;
    }

    public void setDoctorSlot(Long doctorSlot) {
        this.doctorSlot = doctorSlot;
    }

    public Long getStationSlot() {
        return stationSlot;
    }

    public void setStationSlot(Long stationSlot) {
        this.stationSlot = stationSlot;
    }
}
