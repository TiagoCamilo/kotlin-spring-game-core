package com.braveplayers.game.controllers

import com.braveplayers.game.dtos.CharacterDto
import com.braveplayers.game.entities.Character
import com.braveplayers.game.services.CharacterService
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

@WebMvcTest(CharacterController::class)
class CharacterControllerTest {

    private val baseUrl = "characters"

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var service: CharacterService

    private fun getDtoInstance(): CharacterDto {
        val dto = CharacterDto("character1", 100)
        dto.id = 1L;
        return dto;
    }

    private fun getEntityInstance(): Character {
        return Mapper.convert(getDtoInstance())
    }

    @Test
    fun findBy_ResponseEntityWithHttpStatusOKAndCharacterDto() {
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
    fun create_ResponseEntityWithHttpStatusCREATEDAndCharacterDto() {
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
    fun findAll_ResponseEntityWithHttpStatusOKAndCollectionOfCharacterDto() {
        val dtoCollection: Collection<CharacterDto> = listOf(
            CharacterDto("character1"),
            CharacterDto("character2"),
            CharacterDto("character3"),
        )
        val entityCollection: Collection<Character> = dtoCollection.map { Mapper.convert(it) }
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
    fun delete_ResponseEntityWithHttpStatusOKAndCharacterDto() {
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
    fun update_ResponseEntityWithHttpStatusOKAndCharacterDto() {
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