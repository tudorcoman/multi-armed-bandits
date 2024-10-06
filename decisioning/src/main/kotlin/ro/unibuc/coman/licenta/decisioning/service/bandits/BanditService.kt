package ro.unibuc.coman.licenta.decisioning.service.bandits

import ro.unibuc.coman.licenta.decisioning.client.dto.TreatmentStatisticsDTO

interface BanditService {
    fun getDecision(statisticsByTreatment: List<TreatmentStatisticsDTO>, assetId: Long): Long
}