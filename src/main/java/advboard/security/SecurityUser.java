package advboard.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUser extends User
{
    private Long entityId;

    public SecurityUser(String username, String password, Long entityId, Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, true, true, true, true, authorities);
        this.entityId = entityId;
    }

    public Long getEntityId()
    {
        return entityId;
    }
}
