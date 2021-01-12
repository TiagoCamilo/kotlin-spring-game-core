package com.braveplayers.game

import com.braveplayers.game.controllers.PlayerController
import com.braveplayers.game.dtos.PlayerDto
import com.braveplayers.game.entities.Player
import com.braveplayers.game.services.PlayerService
import com.braveplayers.game.util.Mapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class PlayerControllerTest {

    @Autowired
    lateinit var controller: PlayerController

    @MockBean
    lateinit var service: PlayerService

    @Test
    fun findByIdShouldReturnPlayer() {
        val id = 1L
        val dto = PlayerDto("player1", 100)
        val entity = Player(id, "player1", 100)

        BDDMockito.given(service.findById(id)).willReturn(entity)

        Assertions.assertEquals(dto, controller.findById(id))
    }

    @Test
    fun createShouldCreateAndReturnPlayer() {
        val dto = PlayerDto("player1", 100)
        val entity: Player = Mapper.convert(dto)

        BDDMockito.given(service.create(entity)).willReturn(entity)

        Assertions.assertEquals(dto, controller.create(dto))
    }

    @Test
    fun findAllShouldReturnListOfPlayers() {
        val dtoCollection: Collection<PlayerDto> = listOf(
                PlayerDto("player1", 100),
                PlayerDto("player2", 200),
                PlayerDto("player3", 300),
        )
        val entityCollection: Collection<Player> = dtoCollection.map { Mapper.convert(it) }

        BDDMockito.given(service.findAll()).willReturn(entityCollection)

        Assertions.assertEquals(dtoCollection, controller.findAll())
    }

}