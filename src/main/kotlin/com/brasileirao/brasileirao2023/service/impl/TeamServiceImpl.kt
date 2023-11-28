package com.brasileirao.brasileirao2023.service.impl

import com.brasileirao.brasileirao2023.domain.model.Team
import com.brasileirao.brasileirao2023.domain.repository.TeamRepository
import com.brasileirao.brasileirao2023.service.TeamService
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeamServiceImpl(private val teamRepository: TeamRepository) : TeamService {

    @Transactional(readOnly = true)
    override fun findAll(): List<Team> {
        return teamRepository.findAll(Sort.by(Sort.Direction.DESC, "score"))
    }

    @Transactional(readOnly = true)
    override fun findBy(id: String): Team {
        return teamRepository.findById(id).orElseThrow { NoSuchElementException("Time não encontrado com o id: $id")}
    }

    @Transactional
    override fun create(model: Team): Team {
        if(teamRepository.existsById(model.id)){
            throw IllegalArgumentException("Time com o id ${model.id} atualmente já existe")
        }

        return teamRepository.save(model)
    }

    @Transactional
    override fun update(id: String, model: Team): Team {
        if(model.id != id){
            throw IllegalArgumentException("O id do time não corresponde com o id do time a ser atualizado")
        }

        val dbTeam = this.findBy(id)

        dbTeam.name = model.name
        dbTeam.score = model.score

        return teamRepository.save(dbTeam)
    }

    @Transactional
    override fun delete(id: String) {
        val dbTeam = this.findBy(id)
        teamRepository.delete(dbTeam)
    }
}