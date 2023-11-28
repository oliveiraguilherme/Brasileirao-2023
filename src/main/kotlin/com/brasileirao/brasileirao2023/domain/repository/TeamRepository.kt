package com.brasileirao.brasileirao2023.domain.repository

import com.brasileirao.brasileirao2023.domain.model.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, String>
