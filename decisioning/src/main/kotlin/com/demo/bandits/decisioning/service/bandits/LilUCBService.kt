package com.demo.bandits.decisioning.service.bandits

import org.springframework.stereotype.Service
import com.demo.bandits.decisioning.client.dto.TreatmentStatisticsDTO
import com.demo.bandits.decisioning.entity.TreatmentCandidate
import kotlin.math.ln
import kotlin.math.sqrt

@Service
class LilUCBService : BanditService {
    private val delta = 0.9
    private val epsilon = 0.2
    private val a = 1
    private val beta = 0.1

    override fun getDecision(statisticsByTreatment: List<TreatmentStatisticsDTO>, assetId: Long): Long {
        val treatments = statisticsByTreatment.map { TreatmentCandidate(it.treatmentId, it.conversions.toDouble(), it.views) }

        val assetViews = treatments.groupBy{ it.treatment }.mapValues { it.value.sumOf { it.count } }.toMutableMap()
        val assetConversions = treatments.groupBy{ it.treatment }.mapValues { it.value.sumOf { it.reward } }.toMutableMap()

        fun getConfidenceBound(treatment: TreatmentCandidate, treatmentViews: Long): Double {
            val conversionRate = assetConversions[treatment.treatment]!! / treatmentViews
            val term = (1 + beta) * (1 + sqrt(epsilon)) * sqrt((2 * (1 + epsilon) * ln(ln((1 + epsilon) * treatmentViews.toDouble()) / delta)) / treatmentViews)
            return conversionRate + term
        }


        if (treatments.any { assetViews[it.treatment] == 1L }) {
            val treatmentToSample = treatments.first { assetViews[it.treatment] == 1L }.treatment
            assetViews[treatmentToSample] = assetViews[treatmentToSample]!! + 1
            return treatmentToSample
        }

        val treatmentViews = treatments.map { assetViews[it.treatment]!! }.toMutableList()

        val i = (0 until treatments.size).maxByOrNull { i ->
            getConfidenceBound(treatments[i], treatmentViews[i])
        }!!

        val selectedTreatment = treatments[i].treatment
        assetViews[selectedTreatment] = assetViews[selectedTreatment]!! + 1
        assetConversions[selectedTreatment] = assetConversions[selectedTreatment]!! + treatments[i].reward

        return if (treatmentViews.all { it >= 1 + a * treatmentViews.sum() - it }) {
            treatments[treatmentViews.indices.maxByOrNull { treatmentViews[it] }!!].treatment
        } else {
            selectedTreatment
        }
    }

}