package my.test.token.service.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author Sanuja_G
 *   @CreatedTime on 19-10-2021 04:32:22 PM
 */
public class AuthUserDetail implements UserDetails
{
    private final String username;

    private final String password;

    private final Set<? extends GrantedAuthority> grantedAuthorities;

    private final boolean isAccountNonExpired;

    private final boolean isAccountNonLocked;

    private final boolean isCredentialsNonExpired;

    private final boolean isEnabled;

    public AuthUserDetail(String username, String password,
        Set<? extends GrantedAuthority> grantedAuthorities, boolean isAccountNonExpired, boolean isAccountNonLocked,
        boolean isCredentialsNonExpired, boolean isEnabled)
    {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return new ArrayList<>();
    }

    @Override public String getPassword()
    {
        return password;
    }

    @Override public String getUsername()
    {
        return username;
    }

    @Override public boolean isAccountNonExpired()
    {
        return isAccountNonExpired;
    }

    @Override public boolean isAccountNonLocked()
    {
        return isAccountNonLocked;
    }

    @Override public boolean isCredentialsNonExpired()
    {
        return isCredentialsNonExpired;
    }

    @Override public boolean isEnabled()
    {
        return isEnabled;
    }
}
