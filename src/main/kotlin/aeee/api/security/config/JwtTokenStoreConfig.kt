package aeee.api.security.config.oauth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
class JwtTokenStoreConfig {

    @Bean
    fun tokenStore() = JwtTokenStore(jwtAccessTokenConvert())

    @Bean
    fun jwtAccessTokenConvert() = JwtAccessTokenConverter().apply {
        setSigningKey("345345fsdgsf5345")
    }

    @Bean
    @Primary
    fun tokenService() = DefaultTokenServices().apply {
        setTokenStore(tokenStore())
        setSupportRefreshToken(true)
    }

}