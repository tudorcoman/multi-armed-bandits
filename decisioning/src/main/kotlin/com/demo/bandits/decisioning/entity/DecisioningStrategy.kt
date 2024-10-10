package com.demo.bandits.decisioning.entity

enum class DecisioningStrategy {
    THOMPSON_SAMPLING,
    UCB,
    LIL_UCB,
    PAC,
    EPSILON_GREEDY,
    RANDOM
}