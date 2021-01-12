package com.braveplayers.tibia.characters

import com.braveplayers.tibia.characters.entities.Character
import com.braveplayers.tibia.characters.repositories.CharacterRepository
import com.braveplayers.tibia.characters.services.CharacterService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
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
        val entity = Character(id, "char1", 100)

        BDDMockito.given(repository.findById(id)).willReturn(Optional.of(entity))

        Assertions.assertEquals(entity, service.findById(id))
    }

    @Test
    fun createShouldSaveAndReturnCharacter() {
        val id = 1L
        val entity = Character(id, "char1", 100)

        BDDMockito.given(repository.save(entity)).willReturn(entity)

        Assertions.assertEquals(entity, service.create(entity))
    }

    @Test
    fun findAllShouldReturnListOfCharacters() {
        val entityCollection: MutableList<Character> = mutableListOf(
                Character(1, "char1", 100),
                Character(2, "char2", 200),
                Character(3, "char3", 300),
        )

        BDDMockito.given(repository.findAll()).willReturn(entityCollection)

        Assertions.assertEquals(entityCollection, service.findAll())
    }

}
