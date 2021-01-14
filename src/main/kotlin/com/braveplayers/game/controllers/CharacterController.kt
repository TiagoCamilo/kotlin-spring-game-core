package com.braveplayers.game.controllers

import com.braveplayers.game.dtos.CharacterDto
import com.braveplayers.game.entities.Character
import com.braveplayers.game.services.CharacterService
import com.braveplayers.game.util.Mapper
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/characters")
class CharacterController(private val service: CharacterService) {

    @PostMapping
    fun create(@Valid @RequestBody characterDto: CharacterDto): CharacterDto {
        val character: Character = Mapper.convert(characterDto)
        return Mapper.convert(service.create(character))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CharacterDto = Mapper.convert(service.findById(id))

    @GetMapping()
    fun findAll(): Collection<CharacterDto> {
        val characterCollection = service.findAll()
        return characterCollection.map { Mapper.convert(it) }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): CharacterDto = Mapper.convert(service.delete(id))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody characterDto: CharacterDto): CharacterDto {
        characterDto.id = id
        val character: Character = Mapper.convert(characterDto)
        return Mapper.convert(service.update(character))
    }
}