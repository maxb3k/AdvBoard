package advboard.data;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority
{
    ADVERTISER,
    ADMIN,
    SECURITY_ADMIN,
    GUEST;


    public String getAuthority()
    {
        return this.name();
    }
}
