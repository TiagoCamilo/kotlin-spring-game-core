package com.braveplayers.game.controllers

import com.braveplayers.game.dtos.GuildDto
import com.braveplayers.game.entities.Guild
import com.braveplayers.game.services.GuildService
import com.braveplayers.game.util.Mapper
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(GuildController::class)
class GuildControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var controller: GuildController

    @MockBean
    lateinit var service: GuildService

    @Test
    fun findBy_ResponseEntityWithHttpStatusOKAndGuildDto() {
        val dto = GuildDto("guild1")
        dto.id = 1L;
        val entity: Guild = Mapper.convert(dto)
        given(service.findById(dto.id)).willReturn(entity)

        mvc.perform(
            get("/guilds/{id}", dto.id.toString())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(dto.id))
            .andExpect(jsonPath("$.name").value(dto.name))

        verify(service, times(1)).findById(dto.id)
    }

    @Test
    fun create_ResponseEntityWithHttpStatusCREATEDAndGuildDto() {
        val dto = GuildDto("guild1")
        val entity: Guild = Mapper.convert(dto)
        given(service.create(entity)).willReturn(entity)

        mvc.perform(
            post("/guilds")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value(dto.name))

        verify(service, times(1)).create(entity)
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