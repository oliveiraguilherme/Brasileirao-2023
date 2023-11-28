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

    override fun findBy(id: String): Team {
        TODO("Not yet implemented")
    }

    override fun create(model: Team): Team {
        TODO("Not yet implemented")
    }

    override fun update(id: String, model: Team): Team {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }
}