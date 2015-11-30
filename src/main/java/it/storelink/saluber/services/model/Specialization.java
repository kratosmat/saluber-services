package it.storelink.saluber.services.model;

import javax.persistence.*;
//import java.util.Set;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.32
 */

@Entity
@Table(name = "SPECIALIZATION", catalog = "")
public class Specialization {
    private Long _id;
    private String _name;

    /*
    private Set<Doctor> _doctors;

    @ManyToMany(mappedBy="specializations")
    public Set<Doctor> getDoctors() {
        return _doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this._doctors = doctors;
    }
    */

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
