package it.storelink.saluber.services.dao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public interface GenericDAO<T, ID extends Serializable> {

    void save(T item) throws DAOException;

    List<T> list() throws DAOException;

    T find(ID id) throws DAOException;

    void delete(ID id) throws DAOException;

    void delete(T item) throws DAOException;

}
