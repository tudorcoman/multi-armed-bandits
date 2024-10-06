package ro.unibuc.coman.licenta.decisioning.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ro.unibuc.coman.licenta.decisioning.client.ReportingClient
import ro.unibuc.coman.licenta.decisioning.entity.DecisioningStrategy
import ro.unibuc.coman.licenta.decisioning.service.DecisioningService
import ro.unibuc.coman.licenta.decisioning.web.DecisionDTO

@RestController
@RequestMapping("/decisioning")
class DecisioningController (val decisioningService: DecisioningService, val reportingClient: ReportingClient) {

    @GetMapping("/{strategy}/{id}")
    fun decisioning(@PathVariable id: Long, @PathVariable strategy: DecisioningStrategy): DecisionDTO {
        val treatmentId = decisioningService.getDecision(id, strategy)
        val treatment = reportingClient.getTreatmentsForAsset(id).find { it.id == treatmentId }
        return DecisionDTO(treatmentId, treatment!!.name)
    }
}
