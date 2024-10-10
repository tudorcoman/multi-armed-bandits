package com.demo.bandits.decisioning.service.bandits

import org.springframework.stereotype.Service
import com.demo.bandits.decisioning.client.dto.TreatmentStatisticsDTO
import com.demo.bandits.decisioning.entity.TreatmentCandidate
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.sqrt

@Service
class PACService(val randomService: RandomService): BanditService {
    private val alpha = 0.05;
    private val rho = 0.1;
    private val epsilon = 0.0001;

    override fun getDecision(statisticsByTreatment: List<TreatmentStatisticsDTO>, assetId: Long): Long {
        val treatments = statisticsByTreatment.map { TreatmentCandidate(it.treatmentId, it.conversions.toDouble(), it.views) }
        // sample each treatment once
        if (treatments.any { it.count == 0L }) {
            return treatments.first { it.count == 0L }.treatment
        }

        // sort treatments based on conversion rate
        val sortedTreatments = treatments.sortedByDescending { it.reward / it.count }
        val highTreatment = sortedTreatments[0]
        val lowTreatments = sortedTreatments.drop(1)

        // add confidence bounds
        highTreatment.confidenceBound = getLowerConfidenceBound(highTreatment)
        lowTreatments.forEach { it.confidenceBound = getUpperConfidenceBound(it) }

        val upperBoundLowSet = lowTreatments.maxByOrNull { it.confidenceBound }!!

        return if (upperBoundLowSet.confidenceBound - highTreatment.confidenceBound < epsilon) {
            highTreatment.treatment
        } else {
            randomService.getDecision(statisticsByTreatment, assetId)
        }
    }

    private fun getUpperConfidenceBound(arm: TreatmentCandidate): Double {
        return getConfidenceBound(arm, 1)
    }

    private fun getLowerConfidenceBound(arm: TreatmentCandidate): Double {
        return getConfidenceBound(arm, -1)
    }

    private fun getConfidenceBound(arm: TreatmentCandidate, sign: Int): Double {
        val conversionRate = arm.reward / arm.count
        val variance = conversionRate * (1 - conversionRate)
        val stdDev = sqrt(variance)

        val n = arm.count.toDouble()
        val term = sqrt(((2 * (n * rho * rho + 1))/(n * n * rho * rho)) * ln(sqrt(n * rho * rho + 1) / alpha))

        return conversionRate + sign * stdDev * term
    }


}