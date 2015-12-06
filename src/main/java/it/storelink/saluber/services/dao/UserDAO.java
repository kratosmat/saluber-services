package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class UserDAO extends GenericDAOImpl<User, String> {

    public UserDAO() {
        this.entityClass = User.class;
    }

}
