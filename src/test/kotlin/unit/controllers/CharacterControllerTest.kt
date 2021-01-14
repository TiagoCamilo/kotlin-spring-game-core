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

@SpringBootTest
class CharacterControllerTest {

    @Autowired
    lateinit var controller: CharacterController

    @MockBean
    lateinit var service: CharacterService

    @Test
    fun findByIdShouldReturnCharacterDto() {
        val id = 1L
        val dto = CharacterDto("character1", 100)
        val entity = Character(id, "character1", 100)

        given(service.findById(id)).willReturn(entity)

        assertEquals(dto, controller.findById(id))
    }

    @Test
    fun createShouldCreateAndReturnCharacterDto() {
        val dto = CharacterDto("character1", 100)
        val entity: Character = Mapper.convert(dto)

        given(service.create(entity)).willReturn(entity)

        assertEquals(dto, controller.create(dto))
    }

    @Test
    fun findAllShouldReturnCharacterDtos() {
        val dtoCollection: Collection<CharacterDto> = listOf(
                CharacterDto("character1", 100),
                CharacterDto("character2", 200),
                CharacterDto("character3", 300),
        )
        val entityCollection: Collection<Character> = dtoCollection.map { Mapper.convert(it) }

        given(service.findAll()).willReturn(entityCollection)

        assertEquals(dtoCollection, controller.findAll())
    }

    @Test
    fun deleteShouldDeleteAndReturnCharacterDto() {
        val id = 1L
        val dto = CharacterDto("character1", 100)
        val entity: Character = Mapper.convert(dto)

        given(service.delete(id)).willReturn(entity)

        assertEquals(dto, controller.delete(id))
    }

}