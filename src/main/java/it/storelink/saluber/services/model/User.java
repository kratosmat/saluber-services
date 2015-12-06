package it.storelink.saluber.services.model;

import javax.persistence.*;
import java.util.List;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 18.17
 */

@Entity
@Table(name = "USERS", catalog = "")
public class User {

    private String username;
    private String password;
    private String identifier;
    private String firstName;
    private String lastName;
    private List<Role> roles;

    @Id
    @Column(name = "USERNAME", nullable = false, insertable = true, updatable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, insertable = true, updatable = true, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "IDENTIFIER", nullable = false, insertable = true, updatable = true, length = 255)
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Basic
    @Column(name = "FIRSTNAME", nullable = false, insertable = true, updatable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = false, insertable = true, updatable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name="USERNAME"),
            inverseJoinColumns = @JoinColumn(name="ID"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
