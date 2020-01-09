package aeee.api.security.controller

import com.nimbusds.jose.jwk.JWKSet
import net.minidev.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class JwkSetRestController(
        private val jwkSet: JWKSet
) {

    @GetMapping("/.well-known/jwks.json")
    fun keys(): JSONObject {
        return jwkSet.toJSONObject()
    }
}