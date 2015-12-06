package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Role;
import it.storelink.saluber.services.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class RoleDAO extends GenericDAOImpl<Role, Long> {

    public RoleDAO() {
        this.entityClass = Role.class;
    }

}
