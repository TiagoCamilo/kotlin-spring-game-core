package com.braveplayers.game

import com.braveplayers.game.entities.Character
import com.braveplayers.game.exceptions.ResourceNotFoundException
import com.braveplayers.game.repositories.CharacterRepository
import com.braveplayers.game.services.CharacterService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
class CharacterServiceTest {

    @Autowired
    lateinit var service: CharacterService

    @MockBean
    lateinit var repository: CharacterRepository

    @Test
    fun findByIdShouldReturnCharacter() {
        val id = 1L
        val entity = Character(id, "character1", 100)

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
    fun createShouldSaveAndReturnCharacter() {
        val id = 1L
        val entity = Character(id, "character1", 100)

        given(repository.save(entity)).willReturn(entity)

        assertEquals(entity, service.create(entity))
    }

    @Test
    fun findAllShouldReturnCharacters() {
        val entityCollection: MutableList<Character> = mutableListOf(
                Character(1, "character1", 100),
                Character(2, "character2", 200),
                Character(3, "character3", 300),
        )

        given(repository.findAll()).willReturn(entityCollection)

        assertEquals(entityCollection, service.findAll())
    }

    @Test
    fun deleteShouldDeleteAndReturnCharacter() {
        val id = 1L
        val entity = Character(id, "character1", 100)

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
