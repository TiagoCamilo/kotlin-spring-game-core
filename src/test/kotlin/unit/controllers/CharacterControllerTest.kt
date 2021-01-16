package com.braveplayers.game

import com.braveplayers.game.controllers.CharacterController
import com.braveplayers.game.dtos.CharacterDto
import com.braveplayers.game.entities.Character
import com.braveplayers.game.services.CharacterService
import com.braveplayers.game.util.Mapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus

@SpringBootTest
class CharacterControllerTest {

    @Autowired
    lateinit var controller: CharacterController

    @MockBean
    lateinit var service: CharacterService

    @Test
    fun findBy_ResponseEntityWithHttp200AndCharacterDto() {
        val id = 1L
        val dto = CharacterDto("character1", 100)
        val entity = Character(id, "character1", 100)

        given(service.findById(id)).willReturn(entity)
        val responseEntity = controller.findById(id)

        assertEquals(dto, responseEntity.body)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
    }

    @Test
    fun create_ResponseEntityWithHttp201AndCharacterDto() {
        val dto = CharacterDto("character1", 100)
        val entity: Character = Mapper.convert(dto)

        given(service.create(entity)).willReturn(entity)
        val responseEntity = controller.create(dto)

        assertEquals(dto, responseEntity.body)
        assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
    }

    @Test
    fun findAll_ResponseEntityWithHttp200AndCollectionOfCharacterDto() {
        val dtoCollection: Collection<CharacterDto> = listOf(
                CharacterDto("character1", 100),
                CharacterDto("character2", 200),
                CharacterDto("character3", 300),
        )
        val entityCollection: Collection<Character> = dtoCollection.map { Mapper.convert(it) }

        given(service.findAll()).willReturn(entityCollection)
        val responseEntity = controller.findAll()

        assertEquals(dtoCollection, responseEntity.body)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
    }

    @Test
    fun delete_ResponseEntityWithHttp200AndCharacterDto() {
        val id = 1L
        val dto = CharacterDto("character1", 100)
        val entity: Character = Mapper.convert(dto)

        given(service.delete(id)).willReturn(entity)
        val responseEntity = controller.delete(id)

        assertEquals(dto, responseEntity.body)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
    }

    @Test
    fun update_ResponseEntityWithHttp200AndCharacterDto() {
        val id = 1L
        val dto = CharacterDto("character1", 100)
        dto.id = id
        val entity: Character = Mapper.convert(dto)

        given(service.update(entity)).willReturn(entity)
        val responseEntity = controller.update(id, dto)

        assertEquals(dto, responseEntity.body)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
    }

}