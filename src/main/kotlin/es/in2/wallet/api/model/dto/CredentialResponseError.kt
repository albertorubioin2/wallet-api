package es.in2.wallet.api.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CredentialResponseError(
    @JsonProperty("error") val error: String,
    @JsonProperty("description") val description: String
)