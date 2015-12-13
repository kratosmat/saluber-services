package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.dao.UserDAO;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.ExtendedUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

    @RequestMapping("/roles")
    @Transactional
    public ResponseEntity<List<Role>> roles(Principal principal) {
        ResponseEntity<List<Role>> entity = null;
        try {
            if(principal!=null) {
                User user = userDAO.find(principal.getName());
                if(user!=null && user.getRoles().size()>0) {
                    return new ResponseEntity<List<Role>>(user.getRoles(), new HttpHeaders(), HttpStatus.OK);
                }
            }
            return new ResponseEntity<List<Role>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            LOG.error(e);
            entity = new ResponseEntity<List<Role>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping("/userinfo")
    @Transactional
    public ResponseEntity<ExtendedUser> userinfo() {
        ResponseEntity<ExtendedUser> entity;
        try {
            ExtendedUser user = (ExtendedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(user!=null) {
                return new ResponseEntity<ExtendedUser>(user, new HttpHeaders(), HttpStatus.OK);
            }
            return new ResponseEntity<ExtendedUser>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            LOG.error(e);
            entity = new ResponseEntity<ExtendedUser>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

}