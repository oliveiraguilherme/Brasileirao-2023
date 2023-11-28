package com.brasileirao.brasileirao2023.controller

import com.brasileirao.brasileirao2023.controller.dto.TeamDTO
import com.brasileirao.brasileirao2023.service.TeamService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/teams")
@Tag(name = "Teams Controller", description = "RESTful API para administrar times")
class TeamController(private val teamService: TeamService) {

    @GetMapping
    @Operation(summary = "Get All teams", description = "Retrieve a list of all registered teams")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation successful")
    ])
    fun findAll(): ResponseEntity<List<TeamDTO>>{
        val teams = teamService.findAll()
        val teamsDto = teams.map { TeamDTO(it) }
        return ResponseEntity.ok(teamsDto)
    }
}