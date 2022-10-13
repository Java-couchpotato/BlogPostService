package com.example.configurations;

import com.example.repository.BlogSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private BlogSessionRepository sessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorisation");

        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }

        var blogSesion = sessionRepository.findBySessionId(header);

        if (blogSesion == null) {
            filterChain.doFilter(request, response);
            return;
        }
        Authentication key = new UsernamePasswordAuthenticationToken(
                blogSesion,
                null,
                new ArrayList<>()
        );
        SecurityContextHolder.getContext().setAuthentication(key);

        filterChain.doFilter(request, response);
    }
}
