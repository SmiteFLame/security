package com.test.security.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                // user주소에 대해서 인증을 요구한다
                .antMatchers("/user/**").authenticated()
                // manager주소는 ROLE_MANAGER권한이나 ROLE_ADMIN권한이 있어야 접근할 수 있다.
                .antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                // admin주소는 ROLE_ADMIN권한이 있어야 접근할 수 있다.
                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                // 나머지주소는 인증없이 접근 가능하다
                .anyRequest().permitAll()
    }
}