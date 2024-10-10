package com.demo.bandits.decisioning.service;

import org.springframework.stereotype.Service;
import com.demo.bandits.decisioning.client.ReportingClient;
import com.demo.bandits.decisioning.entity.DecisioningStrategy;
import com.demo.bandits.decisioning.service.bandits.EpsilonGreedyService;
import com.demo.bandits.decisioning.service.bandits.LilUCBService;
import com.demo.bandits.decisioning.service.bandits.PACService;
import com.demo.bandits.decisioning.service.bandits.RandomService;
import com.demo.bandits.decisioning.service.bandits.ThompsonSamplingService;
import com.demo.bandits.decisioning.service.bandits.UCBService;

@Service
public class DecisioningService {

    private final RandomService randomService;
    private final EpsilonGreedyService epsilonGreedyService;
    private final ThompsonSamplingService thompsonSamplingService;
    private final UCBService ucbService;
    private final PACService pacService;
    private final LilUCBService lilUCBService;
    private final ReportingClient reportingClient;

    public DecisioningService(RandomService randomService, EpsilonGreedyService epsilonGreedyService, ThompsonSamplingService thompsonSamplingService, UCBService ucbService, PACService pacService, LilUCBService lilUCBService, ReportingClient reportingClient) {
        this.randomService = randomService;
        this.epsilonGreedyService = epsilonGreedyService;
        this.thompsonSamplingService = thompsonSamplingService;
        this.ucbService = ucbService;
        this.pacService = pacService;
        this.lilUCBService = lilUCBService;
        this.reportingClient = reportingClient;
    }

    public Long getDecision(Long assetId, DecisioningStrategy decisioningStrategy) {
        var statistics = reportingClient.getTreatmentsStatisticsForAsset(assetId);
        return switch (decisioningStrategy) {
            case EPSILON_GREEDY -> epsilonGreedyService.getDecision(statistics, assetId);
            case THOMPSON_SAMPLING -> thompsonSamplingService.getDecision(statistics, assetId);
            case UCB -> ucbService.getDecision(statistics, assetId);
            case PAC -> pacService.getDecision(statistics, assetId);
            case LIL_UCB -> lilUCBService.getDecision(statistics, assetId);
            default -> randomService.getDecision(statistics, assetId);
        };
    }
}