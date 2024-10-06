package ro.unibuc.coman.licenta.decisioning.service.bandits

import org.springframework.stereotype.Service
import ro.unibuc.coman.licenta.decisioning.client.dto.TreatmentStatisticsDTO

@Service
class RandomService: BanditService {
    override fun getDecision(statisticsByTreatment: List<TreatmentStatisticsDTO>, assetId: Long): Long {
        return statisticsByTreatment.random().treatmentId
    }
}