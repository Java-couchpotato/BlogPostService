package com.example.configurations;

import com.example.entity.BlogAuthorSession;
import com.example.entity.role.Role;
import com.example.repository.BlogSessionRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@NoArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private BlogSessionRepository sessionRepository;

    @Autowired
    public SecurityFilter(BlogSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }

        BlogAuthorSession blogSession = sessionRepository.findBySessionId(header);

        if (blogSession == null) {
            filterChain.doFilter(request, response);
            return;
        }

        var role = blogSession.getBlogAuthor().getRole();
        var roles = role.equals(String.valueOf(Role.ADMIN)) ?
                List.of(Role.USER, Role.ADMIN) :
                List.of(Role.USER);

        Authentication key = new UsernamePasswordAuthenticationToken(
                blogSession,
                null,
                roles.stream().map(x -> new SimpleGrantedAuthority(x.name())).toList()
        );

        SecurityContextHolder.getContext().setAuthentication(key);

        filterChain.doFilter(request, response);
    }
}
