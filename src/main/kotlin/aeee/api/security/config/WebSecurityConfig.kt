package aeee.api.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

@Configuration
class WebSecurityConfig: WebSecurityConfigurerAdapter() {


    //룰을 무시하는 URL 규칙 설정
    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/css/**")
            .antMatchers("/vendor/**")
            .antMatchers("/js/**")
            .antMatchers("/favicon*/**")
            .antMatchers("/img/**")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/login**").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .formLogin().loginPage("http://localhost:4001/").successHandler(authenticationSuccessHandler())
    }

    @Bean
    fun userDetailService(): UserDetailsService {
        return InMemoryUserDetailsManager(
            User.withUsername("enduser")
                .password("{noop}password")
                .roles("USER")
                .build()
        )
    }

    @Bean
    fun authenticationSuccessHandler(): AuthenticationSuccessHandler = SimpleUrlAuthenticationSuccessHandler().apply {
        setDefaultTargetUrl("/hello")
    }

    @Bean
    fun jwtAuthenticationFilter() = JwtAuthenticationFilter(authenticationManager()).apply {
        setFilterProcessesUrl("/login")
        usernameParameter = "username"
        passwordParameter = "password"
        setAuthenticationSuccessHandler(authenticationSuccessHandler())
    }

}