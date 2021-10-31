package my.test.token.service.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import lombok.AllArgsConstructor;
import my.test.token.service.controller.CustomOauth2RequestFactory;
import my.test.token.service.service.CustomUserDetailService;

/**
 * @Author Sanuja_G
 * @CreatedTime on 19-10-2021 10:50:07 PM
 */
@Configuration
@EnableAuthorizationServer
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter
{
    private PasswordEncoder passwordEncoder;

    private DataSource dataSource;

    private AuthenticationManager authenticationManager;

    private CustomUserDetailService userDetailsService;

    @Bean
    TokenStore jdbcTokenStore()
    {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
    {
        security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .accessTokenConverter(accessTokenConverter())
            .userDetailsService(userDetailsService)
            .authenticationManager(authenticationManager);
    }
    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        return new JwtAccessTokenConverter();
    }
}
