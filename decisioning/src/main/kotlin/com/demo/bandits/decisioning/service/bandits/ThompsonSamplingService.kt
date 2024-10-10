package com.demo.bandits.decisioning.service.bandits

import org.apache.commons.math3.distribution.BetaDistribution
import org.springframework.stereotype.Service
import com.demo.bandits.decisioning.client.dto.TreatmentStatisticsDTO

@Service
class ThompsonSamplingService: BanditService {
    override fun getDecision(statisticsByTreatment: List<TreatmentStatisticsDTO>, assetId: Long): Long {
        return statisticsByTreatment.maxByOrNull {
            val alpha = (it.conversions + 1) * 1.0
            val beta = (it.views - it.conversions + 1) * 1.0
            val betaDistribution = BetaDistribution(alpha, beta)
            betaDistribution.sample()
        }!!.treatmentId
    }
}