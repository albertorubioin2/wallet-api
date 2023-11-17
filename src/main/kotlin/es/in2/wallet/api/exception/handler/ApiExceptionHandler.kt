package es.in2.wallet.api.exception.handler

import es.in2.wallet.api.exception.InvalidToken
import es.in2.wallet.api.exception.NoAuthorizationFoundException
import es.in2.wallet.api.exception.NoSuchQrContentException
import es.in2.wallet.api.model.dto.CredentialResponseError
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    private val log: Logger = LoggerFactory.getLogger(ApiExceptionHandler::class.java)

    @ExceptionHandler(NoSuchQrContentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNoSuchQrContentException(e: Exception): ResponseEntity<Unit> {
        log.error(e.message)
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NoAuthorizationFoundException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNoAuthorizationFoundException(e: Exception): ResponseEntity<Unit> {
        log.error(e.message)
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidToken::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleInvalidToken(e: Exception): ResponseEntity<CredentialResponseError> {
        log.error(e.message)
        val errorResponse = CredentialResponseError(
            error = "invalid_token",
            description = "the request contains the wrong Access Token or the Access Token is missing")
        return ResponseEntity.badRequest().body(errorResponse)
    }

}