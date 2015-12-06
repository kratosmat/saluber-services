package it.storelink.saluber.services.model;


import javax.persistence.*;
import java.util.List;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 18.19
 */

@Entity
@Table(name = "ROLES", catalog = "")
public class Role {

    private Long id;
    private String description;
    //private List<User> users;

    @Id
    @Column(name = "USERNAME", nullable = false, insertable = true, updatable = true, length = 50)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = false, insertable = true, updatable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
    @ManyToMany(mappedBy="roles")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    */
}
