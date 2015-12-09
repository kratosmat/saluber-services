package it.storelink.saluber.services.vo;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * User: kratos
 * Date: 25/07/15
 * Time: 11.13
 */

public class ExtendedUser extends User {

    private String identifier;
    private String firstName;
    private String lastName;
    private Long organization;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Long getOrganization() {
        return organization;
    }

    public void setOrganization(Long organization) {
        this.organization = organization;
    }

    public ExtendedUser(String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                        String personIdentifier, String firstName, String lastName, Long organization) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.setIdentifier(personIdentifier);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setOrganization(organization);
    }

    public boolean hasAuthority(String authority) {
        for(GrantedAuthority grantedAuthority : this.getAuthorities()) {
            if(grantedAuthority.getAuthority().equals(authority)) return true;
        }
        return false;
    }

}
