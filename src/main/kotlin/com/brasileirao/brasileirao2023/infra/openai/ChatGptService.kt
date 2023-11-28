package com.brasileirao.brasileirao2023.infra.openai

import com.brasileirao.brasileirao2023.infra.openai.dto.ChatCompletionRequest
import com.brasileirao.brasileirao2023.infra.openai.dto.ChatCompletionResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
@FeignClient(name = "openapi", url = "https://api.openai.com/v1/")
interface ChatGptService {

    @PostMapping("chat/completions")
    fun createChatCompletion(
        @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String,
        @RequestBody request: ChatCompletionRequest
    ): ChatCompletionResponse
}