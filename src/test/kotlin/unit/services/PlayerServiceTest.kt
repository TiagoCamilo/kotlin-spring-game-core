package com.braveplayers.game

import com.braveplayers.game.entities.Player
import com.braveplayers.game.repositories.PlayerRepository
import com.braveplayers.game.services.PlayerService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
class PlayerServiceTest {

    @Autowired
    lateinit var service: PlayerService

    @MockBean
    lateinit var repository: PlayerRepository

    @Test
    fun findByIdShouldReturnPlayer() {
        val id = 1L
        val entity = Player(id, "char1", 100)

        BDDMockito.given(repository.findById(id)).willReturn(Optional.of(entity))

        Assertions.assertEquals(entity, service.findById(id))
    }

    @Test
    fun createShouldSaveAndReturnPlayer() {
        val id = 1L
        val entity = Player(id, "char1", 100)

        BDDMockito.given(repository.save(entity)).willReturn(entity)

        Assertions.assertEquals(entity, service.create(entity))
    }

    @Test
    fun findAllShouldReturnListOfPlayers() {
        val entityCollection: MutableList<Player> = mutableListOf(
                Player(1, "char1", 100),
                Player(2, "char2", 200),
                Player(3, "char3", 300),
        )

        BDDMockito.given(repository.findAll()).willReturn(entityCollection)

        Assertions.assertEquals(entityCollection, service.findAll())
    }

}
