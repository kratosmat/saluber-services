package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.UserDAO;
import it.storelink.saluber.services.model.Role;
import it.storelink.saluber.services.model.User;
import it.storelink.saluber.services.vo.ExtendedUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private Log LOG = LogFactory.getLog(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO dao) {
        this.userDAO = dao;
    }

    @Transactional
    public List<User> listUsers() throws BusinessException {
        try {
            return userDAO.list();
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    private boolean hasRole(User user, String rolename) {
        for(Role role : user.getRoles()) {
            if (role.getDescription().equals(rolename)) return true;
        }
        return false;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {

            User user = userDAO.find(s);
            if(user!=null && user.getRoles().size()>0) {
                LOG.info("LOADING USER: " + user.getUsername());

                Collection<GrantedAuthority> authorities = getAuthorities(user);

                UserDetails userDetails = new ExtendedUser(
                        user.getUsername(),
                        user.getPassword(),
                        true, true, true, true,
                        authorities,
                        user.getIdentifier(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getOrganization());

                return userDetails;
            }
            else throw new UsernameNotFoundException("Non trovato utente con username: " + s);
        }
        catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    public Collection<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            authList.add(new SimpleGrantedAuthority(role.getDescription()));
            LOG.info("ADDING AUTHORITY: " + role.getDescription());
        }
        return authList;
    }
}
