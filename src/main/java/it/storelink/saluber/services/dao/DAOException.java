package it.storelink.saluber.services.dao;



/**
 * User: kratos
 * Date: 07/06/15
 * Time: 11.26
 */

public class DAOException extends Exception {

    public DAOException(Exception e) {
        super(e.getMessage(), e);
    }

}
