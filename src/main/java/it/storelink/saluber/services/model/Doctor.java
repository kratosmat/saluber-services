package it.storelink.saluber.services.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.26
 */

@Entity
@Table(name = "DOCTOR", catalog = "")
public class Doctor {

    private Long _id;
    private String _firstName;
    private String _lastName;
    private Timestamp _bod;
    private String _phone;
    private String _doctorId;

    private Set<Specialization> _specializations;
    private Set<Hospital> _hospitals;
    private String _cv;

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGen")
    //@SequenceGenerator(name = "SeqGen", sequenceName = "SEQ_DOCTOR", allocationSize = 1)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = -127)
    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    @Basic
    @Column(name = "FIRSTNAME", nullable = true, insertable = true, updatable = true, length = 255)
    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = true, insertable = true, updatable = true, length = 255)
    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    @Basic
    @Column(name = "BOD", nullable = false, insertable = true, updatable = true)
    public Timestamp getBod() {
        return _bod;
    }

    public void setBod(Timestamp bod) {
        this._bod = bod;
    }

    @Basic
    @Column(name = "PHONE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPhone() {
        return _phone;
    }

    public void setPhone(String phone) {
        this._phone = phone;
    }

    @Basic
    @Column(name = "DOCTORID", nullable = true, insertable = true, updatable = true, length = 25)
    public String getDoctorId() {
        return _doctorId;
    }

    public void setDoctorId(String doctorId) {
        this._doctorId = doctorId;
    }

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="SPECIALIZATIONXDOCTOR")
    public Set<Specialization> getSpecializations() {
        return _specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this._specializations = specializations;
    }

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="HOSPITALXDOCTOR")
    public Set<Hospital> getHospitals() {
        return _hospitals;
    }

    public void setHospitals(Set<Hospital> hospitals) {
        this._hospitals = hospitals;
    }

    @Basic
    @Column(name = "CV", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getCv() {
        return _cv;
    }

    public void setCv(String cv) {
        this._cv = cv;
    }

}
