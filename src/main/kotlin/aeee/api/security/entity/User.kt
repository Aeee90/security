package aeee.api.security.entity


class User {
    var id: Long = 0
    var name: String = ""
    var email: String = ""
    var imageUrl: String = ""
    var emailVerified = false
    var password: String = ""
    var provider: AuthProvider? = null
    var providerId: String = ""

}