package it.sara.demo.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // permettiamo endpoint token
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":{\"code\":401,\"message\":\"Missing token\"}}");
            return; // interrompi la catena
        }

        String token = header.substring(7);

        try {

            Claims claims = jwtService.validateToken(token);

            String username = claims.getSubject();
            List<String> roles = claims.get("roles", List.class);

            Authentication auth =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            roles.stream()
                                    .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                                    .toList()
                    );

            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            writeErrorResponse(response, 401, "Token expired");
        } catch (io.jsonwebtoken.JwtException e) {
            writeErrorResponse(response, 401, "Invalid token");
        } catch (Exception e) {
            writeErrorResponse(response, 500, "Authentication error");
        }
    }

    private void writeErrorResponse(HttpServletResponse response,
                                    int code,
                                    String message) throws IOException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        String json = """
        {
            "status": {
                "code": %d,
                "message": "%s",
                "traceId": "%s"
            }
        }
        """.formatted(code, message, java.util.UUID.randomUUID());

        response.getWriter().write(json);
    }

}
