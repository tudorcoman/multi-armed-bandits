package ro.unibuc.coman.licenta.decisioning.service.bandits

import org.springframework.stereotype.Service
import ro.unibuc.coman.licenta.decisioning.client.dto.TreatmentStatisticsDTO

@Service
class EpsilonGreedyService: BanditService {
    private val epsilon: Double = 0.1
    override fun getDecision(statisticsByTreatment: List<TreatmentStatisticsDTO>, assetId: Long): Long {
        return if (Math.random() < epsilon) {
            statisticsByTreatment.random().treatmentId
        } else {
            statisticsByTreatment.maxByOrNull { (if (it.views != 0L) it.conversions / it.views else 0.0).toDouble() }!!.treatmentId
        }
    }
}