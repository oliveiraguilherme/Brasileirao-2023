package com.brasileirao.brasileirao2023.controller

import com.brasileirao.brasileirao2023.controller.dto.TeamDTO
import com.brasileirao.brasileirao2023.service.TeamService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


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

    @GetMapping("/{id}")
    @Operation(summary = "Get One Team", description = "Retorna o registro de um time pelo seu id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation successful"),
        ApiResponse(responseCode = "404", description = "Team Not Found")
    ])
    fun findById(@PathVariable id: String): ResponseEntity<TeamDTO>{
        val team = teamService.findBy(id)
        return ResponseEntity.ok(TeamDTO(team))
    }

    @PostMapping
    @Operation(summary = "Create a Team", description = "Cria um time e retorna as informações do time criado")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation successful"),
        ApiResponse(responseCode = "422", description = "Invalid team data provided")
    ])
    fun create(@RequestBody teamDto: TeamDTO): ResponseEntity<TeamDTO>{
        val team = teamService.create(teamDto.toModel())
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(team.id)
            .toUri()
        return ResponseEntity.created(location).body(TeamDTO(team))
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Team", description = "Atualiza as informações de um time a partir de seu id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation successful"),
        ApiResponse(responseCode = "404", description = "Team Not Found"),
        ApiResponse(responseCode = "422", description = "Invalid team data provided")
    ])
    fun update(@PathVariable id: String, @RequestBody teamDto: TeamDTO): ResponseEntity<TeamDTO>{
        val team = teamService.update(teamDto.id, teamDto.toModel())
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(team.id)
            .toUri()

        return ResponseEntity.created(location).body(TeamDTO(team))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Team", description = "Deleta um time a partir de seu id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation successful"),
        ApiResponse(responseCode = "404", description = "Team Not Found")
    ])
    fun delete(@PathVariable id: String): ResponseEntity<Void>{
        teamService.delete(id)
        return ResponseEntity.noContent().build()
    }
}