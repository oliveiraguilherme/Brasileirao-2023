package com.brasileirao.brasileirao2023.infra.openai.dto

data class ChatCompletionResponse(
    val model: String,
    val choices: List<Choice>
)
