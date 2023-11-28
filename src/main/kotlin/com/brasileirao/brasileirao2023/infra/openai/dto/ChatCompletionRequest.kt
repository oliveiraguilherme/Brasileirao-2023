package com.brasileirao.brasileirao2023.infra.openai.dto

data class ChatCompletionRequest(
    val model: String,
    val message: List<Message>
)
