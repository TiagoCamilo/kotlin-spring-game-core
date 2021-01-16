package com.braveplayers.game

import com.braveplayers.game.controllers.GuildController
import com.braveplayers.game.dtos.GuildDto
import com.braveplayers.game.entities.Guild
import com.braveplayers.game.services.GuildService
import com.braveplayers.game.util.Mapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus

@SpringBootTest
class GuildControllerTest {

    @Autowired
    lateinit var controller: GuildController

    @MockBean
    lateinit var service: GuildService

    @Test
    fun findBy_ResponseEntityWithHttp200AndGuildDto() {
        val id = 1L
        val dto = GuildDto("guild1")
        val entity = Guild(id, "guild1")

        given(service.findById(id)).willReturn(entity)
        val responseEntity = controller.findById(id)

        assertEquals(dto, responseEntity.body)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
    }

    @Test
    fun create_ResponseEntityWithHttp201AndGuildDto() {
        val dto = GuildDto("guild1")
        val entity: Guild = Mapper.convert(dto)

        given(service.create(entity)).willReturn(entity)
        val responseEntity = controller.create(dto)

        assertEquals(dto, responseEntity.body)
        assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
    }

    @Test
    fun findAll_ResponseEntityWithHttp200AndCollectionOfGuildDto() {
        val dtoCollection: Collection<GuildDto> = listOf(
                GuildDto("guild1"),
                GuildDto("guild2"),
                GuildDto("guild3"),
        )
        val entityCollection: Collection<Guild> = dtoCollection.map { Mapper.convert(it) }

        given(service.findAll()).willReturn(entityCollection)
        val responseEntity = controller.findAll()

        assertEquals(dtoCollection, responseEntity.body)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
    }

    @Test
    fun delete_ResponseEntityWithHttp200AndGuildDto() {
        val id = 1L
        val dto = GuildDto("guild1")
        val entity: Guild = Mapper.convert(dto)

        given(service.delete(id)).willReturn(entity)
        val responseEntity = controller.delete(id)

        assertEquals(dto, responseEntity.body)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
    }

    @Test
    fun update_ResponseEntityWithHttp200AndGuildDto() {
        val id = 1L
        val dto = GuildDto("guild1")
        dto.id = id
        val entity: Guild = Mapper.convert(dto)

        given(service.update(entity)).willReturn(entity)
        val responseEntity = controller.update(id, dto)

        assertEquals(dto, responseEntity.body)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
    }

}