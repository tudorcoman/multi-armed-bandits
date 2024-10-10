package com.demo.bandits.decisioning.entity

data class TreatmentCandidate (val treatment: Long, var reward: Double, var count: Long, var confidenceBound: Double = 0.0, var variance: Double = 0.0)
