package com.brasileirao.brasileirao2023.service

import com.brasileirao.brasileirao2023.domain.model.Team

interface SimulateService {
    fun simulate(team1Id: String, team2Id: String): Team
}