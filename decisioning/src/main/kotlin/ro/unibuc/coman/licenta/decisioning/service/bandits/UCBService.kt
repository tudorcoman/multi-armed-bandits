package ro.unibuc.coman.licenta.decisioning.service.bandits

import org.springframework.stereotype.Service
import ro.unibuc.coman.licenta.decisioning.client.dto.TreatmentStatisticsDTO
import kotlin.math.ln
import kotlin.math.sqrt

@Service
class UCBService: BanditService {
    override fun getDecision(statisticsByTreatment: List<TreatmentStatisticsDTO>, assetId: Long): Long {
        val totalViews = statisticsByTreatment.sumOf { it.views }

        return statisticsByTreatment.map {
            val ucb = if (it.views == 0L) {
                Double.MAX_VALUE // incurajam explorarea
            } else {
                val mean = it.conversions.toDouble() / it.views
                val variance = mean * (1 - mean) // binomial distribution (asumam ca fiecare click/non-click este Bernoulli)
                mean + sqrt(2 * ln(totalViews.toDouble()) / it.views * variance)
            }

            Pair(it.treatmentId, ucb)
        }.maxByOrNull { it.second }!!.first
    }
}