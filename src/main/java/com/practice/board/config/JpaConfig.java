package com.practice.board.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {


    @Bean
    public AuditorAware<String> autAuditorAware(){
        return () -> Optional.of("June"); // TODO : 스프링 시큐리티 인증기능 붙일때 수정
    }
}
