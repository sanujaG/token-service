package my.test.token.service.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import my.test.token.service.entity.AuthUserDetail;
import my.test.token.service.entity.User;
import my.test.token.service.enums.Status;
import my.test.token.service.repo.UserRepository;

/**
 * @Author Sanuja_G
 *   @CreatedTime on 19-10-2021 04:32:22 PM
 */
@Service("userDetailsService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailService implements UserDetailsService
{
    private UserRepository userRepository;

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        User user = Optional.ofNullable(userRepository.findByUsernameAndStatus(username, Status.ACTIVE))
            .orElseThrow(() -> new BadCredentialsException("Bad credentials"));
        UserDetails userDetails;
        if (user.getStatus().equals(Status.LOCK)) {
            userDetails = new AuthUserDetail(user.getUsername(), user.getPassword(), Collections.emptySet(), true,
                false, true, true);
        } else if (user.getStatus().equals(Status.BLOCK)) {
            userDetails =
                new AuthUserDetail(user.getUsername(), user.getPassword(), Collections.emptySet(), true, true, true,
                    false);
        } else if (user.getStatus().equals(Status.DELETE)) {
            userDetails =
                new AuthUserDetail(user.getUsername(), user.getPassword(), Collections.emptySet(), true, true, true,
                    false);
        } else if (user.getPasswordStatus().equals(Status.RESET)) {
            userDetails =
                new AuthUserDetail(user.getUsername(), user.getPassword(), Collections.emptySet(), true, true, true,
                    false);
        } else if (user.getPasswordStatus().equals(Status.EXPIRE)) {
            userDetails =
                new AuthUserDetail(user.getUsername(), user.getPassword(), Collections.emptySet(), false, true, true,
                    true);
        } else {
            userDetails =
                new AuthUserDetail(user.getUsername(), user.getPassword(), Collections.emptySet(), true, true, true,
                    true);
        }
        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;
    }
}
