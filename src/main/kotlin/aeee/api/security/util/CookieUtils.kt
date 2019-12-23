package aeee.api.security.util

import org.springframework.util.SerializationUtils
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object CookieUtils {

    object HttpCookie {
        val OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request"
        val REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri"
        val COOKIE_EXPIRE_SECONDS = 180
    }

    fun getCookie(request: HttpServletRequest, name: String): Optional<Cookie> {
        val cookies = request.cookies
        if (cookies != null) {
            for (cookie in cookies) {
                if (cookie.name == name) {
                    return Optional.of(cookie)
                }
            }
        }
        return Optional.empty()
    }

    fun addCookie(response: HttpServletResponse, name: String, value: String, maxAge: Int) {
        val cookie = Cookie(name, value).apply {
            path = "/"
            isHttpOnly = true
            this.maxAge = maxAge
        }
        response.addCookie(cookie)
    }

    fun deleteCookie(request: HttpServletRequest, response: HttpServletResponse, name: String) {
        val cookies = request.cookies
        if (cookies != null) {
            for (cookie in cookies) {
                if (cookie.name == name) response.addCookie(cookie.apply {
                    value = ""
                    path = "/"
                    maxAge = 0
                })
            }
        }
    }

    fun showCookie(request: HttpServletRequest, name: String){
        val cookies = request.cookies
        if (cookies != null) {
            for (cookie in cookies) {
                if (cookie.name == name) {
                    println("$name: ${cookie.value}")
                }
            }
        }
    }

    fun serialize(obj: Any): String = Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(obj))
    fun <T> deserialize(cookie: Cookie, cls: Class<T>) = cls.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.value)))
}