package com.quiz.QuizHub.quiz;

import com.quiz.QuizHub.core.SecurityRules;
import com.quiz.QuizHub.user.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class QuizSecurityRules implements SecurityRules {
    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers(HttpMethod.POST,"quiz").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.PUT,"question/{id}").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE,"question/{id}").hasRole(Role.ADMIN.name());
    }
}
