package aeee.api.security

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.web.bind.annotation.CrossOrigin

@SpringBootApplication
@EnableWebSecurity
//@CrossOrigin("http://localhost:4001")
//@EnableAuthorizationServer
class SecurityApplication

fun main(args: Array<String>) {
	runApplication<SecurityApplication>(*args)
}
