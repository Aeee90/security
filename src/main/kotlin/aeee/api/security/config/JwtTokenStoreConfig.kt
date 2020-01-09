package aeee.api.security.config

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.ClassPathResource
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import org.springframework.security.web.access.AccessDeniedHandler
import java.security.interfaces.RSAPublicKey

@Configuration
class JwtTokenStoreConfig {

    private val ksFile = ClassPathResource("bael-jwt.jks")
    private val ksFactory = KeyStoreKeyFactory(ksFile, "bael-pass".toCharArray())

    @Bean
    fun tokenStore() = JwtTokenStore(jwtAccessTokenConvert())

    @Bean
    fun jwtAccessTokenConvert() = JwtAccessTokenConverter().apply {
        setKeyPair(ksFactory.getKeyPair("bael-oauth-jwt"))
    }

    @Bean
    @Primary
    fun tokenService() = DefaultTokenServices().apply {
        setTokenStore(tokenStore())
        setSupportRefreshToken(true)
    }

    @Bean
    fun jwkSet(): JWKSet = JWKSet(
        RSAKey.Builder(ksFactory.getKeyPair("bael-oauth-jwt").public as RSAPublicKey).run {
            keyUse(KeyUse.SIGNATURE)
            algorithm(JWSAlgorithm.RS256)
            keyID("bael-key-id")
            build()
        }
    )

}

