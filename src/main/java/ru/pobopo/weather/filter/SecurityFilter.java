package ru.pobopo.weather.filter;


import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.pobopo.weather.service.AuthService;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER = "Own-Auth-UserName";

    private final AuthService authService;

    @Autowired
    public SecurityFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        final String authHeaderValue = request.getHeader(AUTH_HEADER);
        if (StringUtils.isNotBlank(authHeaderValue)) {
            String[] credits = authHeaderValue.split(";");
            if (credits.length == 2 && authService.authUser(credits[0], credits[1])) {
                UserDetails details = new User(credits[0], credits[1], new ArrayList<>());
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    details, null, details.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        } else {
            log.warn("Empty auth header!");
        }

        filterChain.doFilter(request, response);
    }
}
