package Cyber_Community.entities;


import java.util.HashSet;
import java.util.Set;

/**
 * Defines the possible {@link User} roles.
 */


public enum Role {

    ADMIN,

    USER,

    MANAGER,

    ANONYMOUS;

    public Set<String> toSpringRoles() {
        Set<String> roles = new HashSet<>();
        for (String p : this.toSpringRoles()) {
            roles.add("ROLE_" +p);
        }
        return roles;
    }

    public String getName(){
        switch (this){
            case USER -> {
                return "USER";
            }
            case MANAGER -> {
                return "MANAGER";
            }
            case ADMIN -> {
                return "ADMIN";
            }
            case ANONYMOUS -> {
                return "ANONYMOUS";
            }
        }
        return null;
    }

}
