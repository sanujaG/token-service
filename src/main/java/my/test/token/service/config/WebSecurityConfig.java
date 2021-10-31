package my.test.token.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.AllArgsConstructor;
import my.test.token.service.entrypoint.CustomAccessDeniedHandler;
import my.test.token.service.entrypoint.CustomAuthenticationEntryPoint;
import my.test.token.service.service.CustomUserDetailService;

/**
 * @Author Sanuja_G
 * @CreatedTime on 19-10-2021 10:23:46 PM
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private CustomUserDetailService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(getPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler()
    {
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/oauth/token/**").permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler
            (accessDeniedHandler());
    }

}
