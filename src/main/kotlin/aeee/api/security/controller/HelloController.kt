package aeee.api.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello() = "Hello, Security!"

    @GetMapping("/login/oauth2/code/facebook")
    fun facebook(): String {
        return "facebook"
    }

    @GetMapping("/test/hello")
    fun testHello(): String{
        return "is test, Hello!"
    }

}