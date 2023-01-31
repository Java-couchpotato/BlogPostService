package com.example.configurations;

import com.example.entity.BlogAuthorSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BlogConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            var key = SecurityContextHolder.getContext().getAuthentication();
            if (key == null) {
                return Optional.empty();
            }
            var session = (BlogAuthorSession) key.getPrincipal();
            return Optional.of(session.getSessionId());
        };
    }

}
