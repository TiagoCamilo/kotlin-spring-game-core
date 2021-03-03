package com.braveplayers.game.services

import com.braveplayers.game.entities.Character
import com.braveplayers.game.exceptions.classes.ResourceNotFoundException
import com.braveplayers.game.repositories.CharacterRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@WebMvcTest(CharacterService::class)
class CharacterServiceTest {

    @Autowired
    lateinit var service: CharacterService

    @MockBean
    lateinit var repository: CharacterRepository

    @MockBean
    lateinit var messagingService: MessagingService

    @Test
    fun findById_Character() {
        val entity = getEntityInstance()

        given(repository.findById(entity.id)).willReturn(Optional.of(entity))

        assertEquals(entity, service.findById(entity.id))
    }

    @Test
    fun findById_ThrowResourceNotFoundException() {
        val id = 1L

        val exception = assertThrows(ResourceNotFoundException::class.java) {
            service.findById(id)
        }

        assertEquals("Not Found", exception.message)
    }

    @Test
    fun create_Character() {
        val entity = getEntityInstance()

        given(repository.save(entity)).willReturn(entity)

        assertEquals(entity, service.create(entity))
        verify(repository, times(1)).save(entity)
    }

    @Test
    fun findAll_Characters() {
        val entityCollection: MutableList<Character> = mutableListOf(
            getEntityInstance()
        )

        given(repository.findAll()).willReturn(entityCollection)

        assertEquals(entityCollection, service.findAll())
    }

    @Test
    fun delete_Character() {
        val entity = getEntityInstance()

        given(repository.findById(entity.id)).willReturn(Optional.of(entity))

        assertEquals(entity, service.delete(entity.id))
        verify(repository, times(1)).delete(entity)
    }

    @Test
    fun delete_ThrowResourceNotFoundException() {
        val entity = getEntityInstance()

        val exception = assertThrows(ResourceNotFoundException::class.java) {
            service.delete(entity.id)
        }

        assertEquals("Not Found", exception.message)
        verify(repository, times(0)).delete(entity)

    }

    @Test
    fun update_Character() {
        val entity = getEntityInstance()

        given(repository.findById(entity.id)).willReturn(Optional.of(entity))
        given(repository.save(entity)).willReturn(entity)

        assertEquals(entity, service.update(entity))
        verify(repository, times(1)).save(entity)
    }

    @Test
    fun update_ThrowResourceNotFoundException() {
        val entity = getEntityInstance()

        val exception = assertThrows(ResourceNotFoundException::class.java) {
            service.update(entity)
        }

        assertEquals("Not Found", exception.message)
    }

    companion object {
        fun getEntityInstance(): Character {
            val entity = Character(
                id = 1L,
                name = "character01",
                level = 100,
                guildName = "guild01",
                worldName = "world01",
                vocation = "vocation01"
            )

            return entity;
        }
    }

}
