package it.storelink.saluber.services.model;


import javax.persistence.*;
import java.util.List;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.05
 */
@Entity
@DiscriminatorValue("DOCTOR")
public class MonthDoctor extends Month {

    private String username;

    @Basic
    @Column(name = "USERNAME", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
