package com.braveplayers.game.controllers

import com.braveplayers.game.dtos.GuildDto
import com.braveplayers.game.entities.Guild
import com.braveplayers.game.services.GuildService
import com.braveplayers.game.util.Mapper
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(GuildController::class)
class GuildControllerTest {

    private val baseUrl = "guilds"

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var service: GuildService

    private fun getDtoInstance(): GuildDto {
        val dto = GuildDto("guild1")
        dto.id = 1L;
        return dto;
    }

    private fun getEntityInstance(): Guild {
        return Mapper.convert(getDtoInstance())
    }

    @Test
    fun findBy_ResponseEntityWithHttpStatusOKAndGuildDto() {
        val dto = getDtoInstance()
        val entity = getEntityInstance()
        given(service.findById(dto.id)).willReturn(entity)

        mvc.perform(
            get("/$baseUrl/{id}", dto.id.toString())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(dto.id))
            .andExpect(jsonPath("$.name").value(dto.name))

        verify(service, times(1)).findById(dto.id)
    }

    @Test
    fun create_ResponseEntityWithHttpStatusCREATEDAndGuildDto() {
        val dto = getDtoInstance()
        val entity = getEntityInstance()
        given(service.create(entity)).willReturn(entity)

        mvc.perform(
            post("/$baseUrl")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").value(dto.id))
            .andExpect(jsonPath("$.name").value(dto.name))

        verify(service, times(1)).create(entity)
    }

    @Test
    fun findAll_ResponseEntityWithHttpStatusOKAndCollectionOfGuildDto() {
        val dtoCollection: Collection<GuildDto> = listOf(
            GuildDto("guild1"),
            GuildDto("guild2"),
            GuildDto("guild3"),
        )
        val entityCollection: Collection<Guild> = dtoCollection.map { Mapper.convert(it) }
        given(service.findAll()).willReturn(entityCollection)

        mvc.perform(
            get("/$baseUrl")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(3))
            .andExpect(jsonPath("$.[*].id").exists())
            .andExpect(jsonPath("$.[*].name").exists())

        verify(service, times(1)).findAll()
    }

    @Test
    fun delete_ResponseEntityWithHttpStatusOKAndGuildDto() {
        val dto = getDtoInstance()
        val entity = getEntityInstance()
        given(service.delete(dto.id)).willReturn(entity)

        mvc.perform(
            delete("/$baseUrl/{id}", dto.id.toString())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(dto.id))
            .andExpect(jsonPath("$.name").value(dto.name))

        verify(service, times(1)).delete(dto.id)
    }

    @Test
    fun update_ResponseEntityWithHttpStatusOKAndGuildDto() {
        val dto = getDtoInstance()
        val entity = getEntityInstance()
        given(service.update(entity)).willReturn(entity)

        mvc.perform(
            put("/$baseUrl/{id}", dto.id.toString())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(dto.id))
            .andExpect(jsonPath("$.name").value(dto.name))

        verify(service, times(1)).update(entity)
    }

}