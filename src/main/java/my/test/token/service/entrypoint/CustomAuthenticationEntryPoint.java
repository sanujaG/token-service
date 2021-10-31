package my.test.token.service.entrypoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @Author Sanuja_G
 * @CreatedTime on 21-10-2021 03:01:16 PM
 */
@Component
public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest httpServletRequest,
        HttpServletResponse response, AuthenticationException authEx)
        throws IOException
    {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());
    }

    @Override
    public void afterPropertiesSet()
    {
        setRealmName("token-service");
        super.afterPropertiesSet();
    }
}
