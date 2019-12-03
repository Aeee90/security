package aeee.api.security.config

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    authenticationManager: AuthenticationManager
): UsernamePasswordAuthenticationFilter(){

    private val postOnly = true

    init {
        setAuthenticationManager(authenticationManager)
    }

    /*
     * 해당 필터에서 인증 프로세스 이전에 요청에서 사용자 정보를 가져와서
     * Authentication 객체를 인증 프로세스 객체에게 전달하는 역할
     */

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        if(postOnly && request.method != "POST")
            throw AuthenticationServiceException("Authentication method not supported: " + request.method)


        val username: String = obtainUsername(request) ?: ""
        val password: String = obtainPassword(request) ?: ""


        val authRequest = UsernamePasswordAuthenticationToken(username , password);

        setDetails(request, authRequest);

        return authenticationManager.authenticate(authRequest)
    }

}