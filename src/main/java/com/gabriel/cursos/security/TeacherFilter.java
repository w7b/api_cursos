package com.gabriel.cursos.security;

import com.gabriel.cursos.utils.TeacherProviderJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TeacherFilter extends OncePerRequestFilter {

    @Autowired
    private TeacherProviderJWT teacherProviderJWT;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var header = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/courses")) {
            if (header != null) {
                var token = this.teacherProviderJWT.validateToken(header);

                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                request.setAttribute("professor_id", token.getSubject());

                var roles = token.getClaim("roles").asList(Object.class);
                var grants = roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.toString().toUpperCase())
                        ).toList();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(token, null, grants);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }


        filterChain.doFilter(request, response);
    }
}
