package it.storelink.saluber.services.model;


import javax.persistence.*;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.40
 */

@Entity
@Table(name = "MEDICAL_TEST_TYPE", catalog = "")
public class MedicalTestType {

    private Long _id;
    private String _name;

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = -127)
    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true)
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

}
