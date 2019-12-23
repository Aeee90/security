package aeee.api.security

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

fun main(args: Array<String>) {
	runApplication<SecurityApplication>(*args)
}

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
@RestController
class SecurityApplication {

	@RequestMapping(value = ["/user"], produces=["application/json"])
	fun user(user: OAuth2Authentication): Map<String, Any> {
		return hashMapOf<String, Any>(Pair("String", user.userAuthentication.principal)
				, Pair("authorities" ,AuthorityUtils.authorityListToSet(user.userAuthentication.authorities))
		)
	}
}
