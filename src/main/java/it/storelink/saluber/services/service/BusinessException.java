package it.storelink.saluber.services.service;



/**
 * User: kratos
 * Date: 07/06/15
 * Time: 11.26
 */

public class BusinessException extends Exception {

    public BusinessException(Exception e) {
        super(e);
    }

    public BusinessException(String message) {
        super(message);
    }
}
