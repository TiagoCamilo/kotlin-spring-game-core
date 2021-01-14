package com.braveplayers.game

import com.braveplayers.game.entities.Player
import com.braveplayers.game.exceptions.ResourceNotFoundException
import com.braveplayers.game.repositories.PlayerRepository
import com.braveplayers.game.services.PlayerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.lang.RuntimeException
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
        val entity = Player(id, "player1", 100)

        given(repository.findById(id)).willReturn(Optional.of(entity))
        assertEquals(entity, service.findById(id))
    }

    @Test
    fun findByIdShouldThrowException() {
        val id = 1L

        val exception = assertThrows(ResourceNotFoundException::class.java) {
            service.findById(id)
        }

        assertEquals("Not Found", exception.message)
    }

    @Test
    fun createShouldSaveAndReturnPlayer() {
        val id = 1L
        val entity = Player(id, "player1", 100)

        given(repository.save(entity)).willReturn(entity)

        assertEquals(entity, service.create(entity))
    }

    @Test
    fun findAllShouldReturnListOfPlayers() {
        val entityCollection: MutableList<Player> = mutableListOf(
                Player(1, "player1", 100),
                Player(2, "player2", 200),
                Player(3, "player3", 300),
        )

        given(repository.findAll()).willReturn(entityCollection)

        assertEquals(entityCollection, service.findAll())
    }

    @Test
    fun deleteShouldDeleteAndReturnPlayer() {
        val id = 1L
        val entity = Player(id, "player1", 100)

        given(repository.findById(id)).willReturn(Optional.of(entity))

        assertEquals(entity, service.delete(id))
        verify(repository, times(1)).delete(entity)
    }

    @Test
    fun deleteShouldThrowException() {
        val id = 1L

        val exception = assertThrows(ResourceNotFoundException::class.java) {
            service.delete(id)
        }

        assertEquals("Not Found", exception.message)
    }
}
