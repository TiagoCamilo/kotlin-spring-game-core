package com.braveplayers.game.services

import com.braveplayers.game.entities.Guild
import com.braveplayers.game.exceptions.classes.ResourceNotFoundException
import com.braveplayers.game.repositories.GuildRepository
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

@WebMvcTest(GuildService::class)
class GuildServiceTest {

    @Autowired
    lateinit var service: GuildService

    @MockBean
    lateinit var repository: GuildRepository

    @Test
    fun findById_Guild() {
        val id = 1L
        val entity = Guild(id, "guild1")

        given(repository.findById(id)).willReturn(Optional.of(entity))

        assertEquals(entity, service.findById(id))
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
    fun create_Guild() {
        val id = 1L
        val entity = Guild(id, "character1")

        given(repository.save(entity)).willReturn(entity)

        assertEquals(entity, service.create(entity))
        verify(repository, times(1)).save(entity)
    }

    @Test
    fun findAll_Guild() {
        val entityCollection: MutableList<Guild> = mutableListOf(
            Guild(1, "guild1"),
            Guild(2, "guild2"),
            Guild(3, "guild3"),
        )

        given(repository.findAll()).willReturn(entityCollection)

        assertEquals(entityCollection, service.findAll())
    }

    @Test
    fun delete_Guild() {
        val id = 1L
        val entity = Guild(id, "guild1")

        given(repository.findById(id)).willReturn(Optional.of(entity))

        assertEquals(entity, service.delete(id))
        verify(repository, times(1)).delete(entity)
    }

    @Test
    fun delete_ThrowResourceNotFoundException() {
        val id = 1L
        val entity = Guild(id, "guild1")

        val exception = assertThrows(ResourceNotFoundException::class.java) {
            service.delete(id)
        }

        assertEquals("Not Found", exception.message)
        verify(repository, times(0)).delete(entity)

    }

    @Test
    fun update_Guild() {
        val id = 1L
        val entity = Guild(id, "guild1")

        given(repository.findById(id)).willReturn(Optional.of(entity))
        given(repository.save(entity)).willReturn(entity)

        assertEquals(entity, service.update(entity))
        verify(repository, times(1)).save(entity)
    }

    @Test
    fun update_ThrowResourceNotFoundException() {
        val id = 1L
        val entity = Guild(id, "guild1")

        val exception = assertThrows(ResourceNotFoundException::class.java) {
            service.update(entity)
        }

        assertEquals("Not Found", exception.message)
    }

}
