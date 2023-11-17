package es.in2.wallet.api.exception

private const val INVALID_TOKEN_MESSAGE = "Invalid token"
class InvalidToken(message: String) : Exception(message) {
    constructor() : this(INVALID_TOKEN_MESSAGE)
}