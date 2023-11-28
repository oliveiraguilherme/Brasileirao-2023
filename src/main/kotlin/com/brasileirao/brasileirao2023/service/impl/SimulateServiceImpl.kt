package com.brasileirao.brasileirao2023.service.impl

import com.brasileirao.brasileirao2023.domain.model.Team
import com.brasileirao.brasileirao2023.infra.openai.ChatGptService
import com.brasileirao.brasileirao2023.infra.openai.dto.ChatCompletionRequest
import com.brasileirao.brasileirao2023.infra.openai.dto.Message
import com.brasileirao.brasileirao2023.service.SimulateService
import com.brasileirao.brasileirao2023.service.TeamService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SimulateServiceImpl(
    private val teamService: TeamService,
    private val chatGptService: ChatGptService,
    @Value("\${openai.api-key}")
    private val openAiApiKey: String
): SimulateService {
    val brasileiraoTeams = teamService.findAll()
    override fun simulate(team1Id: String, team2Id: String): Team {
        val team1 = teamService.findBy(team1Id)
        val team2 = teamService.findBy(team2Id)

        return if(team1.score > team2.score) team1 else team2

        /*
        val trainingData = brasileiraoTeams.joinToString("\n") { "${it.id} (${it.score})" }

        val authorization = "Bearer $openAiApiKey"
        val request = ChatCompletionRequest("gpt-3.5-turbo", message = listOf(
            Message("system",
                """
                    Atue como um modelo de análise estatística para simulação de partidas de futebol do brasileirão.
                    Considere os seguintes dados de treinamento, no formato {SIGLA_DO_TIME} ({PONTOS_NO_RANKING_DA_CBF}):
                    $trainingData
                """.trimIndent()),
            Message(
                "user", "Simule a partida entre ${team1.id} vs ${team2.id}, Me envie como resposta apenas a sigla da seleção vencedora, não divague!")
        ))
        val response = chatGptService.createChatCompletion(authorization, request)

        return if (response.choices.first().message.content == team1.id) team1 else team2
        */
    }
}