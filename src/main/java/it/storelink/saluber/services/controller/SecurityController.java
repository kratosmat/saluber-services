package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.dao.UserDAO;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.service.ReferenceEntityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    private Log LOG = LogFactory.getLog(SecurityController.class);

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @RequestMapping("/roles/{username}")
    @Transactional
    public ResponseEntity<List<Role>> roles(@PathVariable String username) {
        ResponseEntity<List<Role>> entity = null;
        try {
            User user = userDAO.find(username);

            if(user!=null && user.getRoles().size()>0) {
                entity = new ResponseEntity<List<Role>>(user.getRoles(), new HttpHeaders(), HttpStatus.OK);
            }
            else {
                entity = new ResponseEntity<List<Role>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            LOG.error(e);
            entity = new ResponseEntity<List<Role>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

}