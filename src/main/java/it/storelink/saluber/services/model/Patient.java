package it.storelink.saluber.services.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.26
 */

@Entity
@Table(name = "PATIENT", catalog = "")
public class Patient {

    private Long _id;
    private String _firstName;
    private String _lastName;
    private Timestamp _bod;
    private String _phone;
    private String _sanitaryId;

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGen")
    //@SequenceGenerator(name = "SeqGen", sequenceName = "SEQ_PATIENT", allocationSize = 1)
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
    @Column(name = "SANITARYID", nullable = true, insertable = true, updatable = true, length = 16)
    public String getSanitaryId() {
        return _sanitaryId;
    }

    public void setSanitaryId(String sanitaryId) {
        this._sanitaryId = sanitaryId;
    }

}
