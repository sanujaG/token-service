package my.test.token.service.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j2;

/**
 * @Author Sanuja_G
 *   @CreatedTime on 22-10-2021 11:02:53 AM
 */

@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler
{
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException e) throws IOException, ServletException
    {
        Authentication auth
            = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.info("User: " + auth.getName()
                + " attempted to access the protected URL: "
                + request.getRequestURI());
        }

        response.sendRedirect(request.getContextPath() + "/accessDenied");
    }
}
