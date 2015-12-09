package it.storelink.saluber.services.vo;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.26
 */

public class DoctorVO {

    private Long _id;
    private String _firstName;
    private String _lastName;
    private Timestamp _bod;
    private String _phone;
    private String _doctorId;
    private String _cv;
    private String photo;
    private Set<Long> _specializations = new LinkedHashSet<Long>();
    private Set<Long> _hospitals = new LinkedHashSet<Long>();

    public Long getId() {
        return _id;
    }
    public void setId(Long id) {
        this._id = id;
    }

    public String getFirstName() {
        return _firstName;
    }
    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    public String getLastName() {
        return _lastName;
    }
    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    public Timestamp getBod() {
        return _bod;
    }
    public void setBod(Timestamp bod) {
        this._bod = bod;
    }

    public String getPhone() {
        return _phone;
    }
    public void setPhone(String phone) {
        this._phone = phone;
    }

    public String getDoctorId() {
        return _doctorId;
    }
    public void setDoctorId(String doctorId) {
        this._doctorId = doctorId;
    }

    public Set<Long> getSpecializations() {
        return _specializations;
    }
    public void setSpecializations(Set<Long> specializations) {
        this._specializations = specializations;
    }

    public Set<Long> getHospitals() {
        return _hospitals;
    }
    public void setHospitals(Set<Long> hospitals) {
        this._hospitals = hospitals;
    }

    public String getCv() {
        return _cv;
    }
    public void setCv(String cv) {
        this._cv = cv;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
