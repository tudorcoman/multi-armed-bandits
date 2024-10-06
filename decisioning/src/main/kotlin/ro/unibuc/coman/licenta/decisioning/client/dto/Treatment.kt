package ro.unibuc.coman.licenta.decisioning.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Treatment(@JsonProperty("id") val id: Long, @JsonProperty("name") val name: String, @JsonProperty("assetId") val assetId: String)
