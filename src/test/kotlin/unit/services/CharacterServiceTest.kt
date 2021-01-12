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
        val obj = Character(id, "Char1", 100)

        BDDMockito.given(repository.findById(id)).willReturn(Optional.of(obj))

        Assertions.assertEquals(obj, service.findById(id))
    }

    @Test
    fun createShouldSaveAndReturnCharacter() {
        val id = 99L
        val obj = Character(id, "Char1", 100)

        BDDMockito.given(repository.save(obj)).willReturn(obj)

        Assertions.assertEquals(obj, service.create(obj))
    }

}
