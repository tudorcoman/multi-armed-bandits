package com.demo.bandits.decisioning.service.bandits

import org.springframework.stereotype.Service
import com.demo.bandits.decisioning.client.dto.TreatmentStatisticsDTO

@Service
class RandomService: BanditService {
    override fun getDecision(statisticsByTreatment: List<TreatmentStatisticsDTO>, assetId: Long): Long {
        return statisticsByTreatment.random().treatmentId
    }
}