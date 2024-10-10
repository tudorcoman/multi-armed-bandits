package com.demo.bandits.decisioning.service.bandits

import com.demo.bandits.decisioning.client.dto.TreatmentStatisticsDTO

interface BanditService {
    fun getDecision(statisticsByTreatment: List<TreatmentStatisticsDTO>, assetId: Long): Long
}