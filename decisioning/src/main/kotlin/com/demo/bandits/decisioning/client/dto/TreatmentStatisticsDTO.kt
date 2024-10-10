package com.demo.bandits.decisioning.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TreatmentStatisticsDTO(@JsonProperty("treatmentId") val treatmentId: Long, @JsonProperty("views") val views: Long, @JsonProperty("conversions") val conversions: Long)

