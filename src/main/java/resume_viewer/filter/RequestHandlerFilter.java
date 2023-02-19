package resume_viewer.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import resume_viewer.service.RedisService;
import resume_viewer.service.UserDetail;

import java.io.IOException;

@Order(1)
public class RequestHandlerFilter extends OncePerRequestFilter {
    @Autowired
    RedisService redisService;

    @Autowired
    UserDetailsService userDetailsService;

    public RequestHandlerFilter(RedisService redisService, UserDetailsService userDetailsService) {
        this.redisService = redisService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        Object redisToken;
        if (token == null || (redisToken = redisService.get(token)) == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        redisService.update(token, redisToken.toString());
        UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(redisToken.toString());
        UsernamePasswordAuthenticationToken token1 = new
                UsernamePasswordAuthenticationToken(userDetail.getEmail(),
                userDetail.getPassword(), userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token1);
        filterChain.doFilter(request, response);
    }
}
