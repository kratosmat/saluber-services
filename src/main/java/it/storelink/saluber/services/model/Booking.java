package it.storelink.saluber.services.model;


import javax.persistence.*;
import java.sql.Timestamp;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.38
 */

@Entity
@Table(name = "BOOKING", catalog = "")
public class Booking {

    private Long _id;
    private Timestamp _dateStart;
    private Station _station;
    private Doctor _doctor;
    private Patient _patient;
    private Specialization _specialization;
    private Long doctorSlot;
    private Long stationSlot;



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGen")
    @SequenceGenerator(name = "SeqGen", sequenceName = "SEQ_BOOKING", allocationSize = 1)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = -127)
    public Long getId() {
        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    @Basic
    @Column(name = "DATESTART", nullable = false, insertable = true, updatable = true)
    public Timestamp getDateStart() {
        return _dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this._dateStart = dateStart;
    }

    @ManyToOne
    @JoinColumn(name = "STATION", referencedColumnName = "ID", nullable = false)
    public Station getStation() {
        return _station;
    }

    public void setStation(Station station) {
        this._station = station;
    }

    @ManyToOne
    @JoinColumn(name = "DOCTOR", referencedColumnName = "ID", nullable = false)
    public Doctor getDoctor() {
        return _doctor;
    }

    public void setDoctor(Doctor doctor) {
        this._doctor = doctor;
    }

    @ManyToOne
    @JoinColumn(name = "PATIENT", referencedColumnName = "ID", nullable = false)
    public Patient getPatient() {
        return _patient;
    }

    public void setPatient(Patient patient) {
        this._patient = patient;
    }

    @ManyToOne
    @JoinColumn(name = "SPECIALIZATION", referencedColumnName = "ID", nullable = false)
    public Specialization getSpecialization() {
        return _specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this._specialization = specialization;
    }

    @Basic
    @Column(name = "DOCTOR_SLOT", nullable = false, insertable = true, updatable = true)
    public Long getDoctorSlot() {
        return doctorSlot;
    }

    public void setDoctorSlot(Long doctorSlot) {
        this.doctorSlot = doctorSlot;
    }

    @Basic
    @Column(name = "STATION_SLOT", nullable = false, insertable = true, updatable = true)
    public Long getStationSlot() {
        return stationSlot;
    }

    public void setStationSlot(Long stationSlot) {
        this.stationSlot = stationSlot;
    }
}
