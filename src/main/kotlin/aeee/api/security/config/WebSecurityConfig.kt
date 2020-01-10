package aeee.api.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.jwt.JwtDecoder


@Configuration
class WebSecurityConfig(
    private val jwtDecoder: JwtDecoder
): WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        return super.userDetailsService()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("spoi2308")
                .password("{noop}password1")
                .roles("ADMIN")

    }

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .anyRequest().authenticated().and()
            .oauth2Login().and()
            .oauth2ResourceServer()
                .jwt {jwt -> jwt.decoder(jwtDecoder)}
    }
}