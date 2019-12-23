package aeee.api.security.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer

@Configuration
class AuthorizationServerConfig(
    private val authenticationManager: AuthenticationManager
    , private val userDetailsService: UserDetailsService
    , private val jwtTokenStoreConfig: JwtTokenStoreConfig
): AuthorizationServerConfigurerAdapter() {



    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
            .withClient("eagleeye")
            .secret("{noop}thisissecret")
            .scopes("webclient", "mobileclient")
            .authorizedGrantTypes("refresh_token", "password", "client_credentials")
            //.redirectUris("http://localhost:8081/oauth/login/client-app")
    }


    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {

        endpoints
                .tokenStore(jwtTokenStoreConfig.tokenStore())
                .accessTokenConverter(jwtTokenStoreConfig.jwtAccessTokenConvert())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
    }

}