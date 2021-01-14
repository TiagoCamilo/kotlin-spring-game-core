package com.braveplayers.game.controllers

import com.braveplayers.game.dtos.CharacterDto
import com.braveplayers.game.entities.Character
import com.braveplayers.game.services.CharacterService
import com.braveplayers.game.util.Mapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/characters")
class CharacterController(private val service: CharacterService) {

    @PostMapping
    fun create(@Valid @RequestBody characterDto: CharacterDto): ResponseEntity<CharacterDto> {
        val character: Character = Mapper.convert(characterDto)
        val entityCreated: CharacterDto = Mapper.convert(service.create(character))

        return ResponseEntity<CharacterDto>(entityCreated, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CharacterDto> {
        val entityFound: CharacterDto = Mapper.convert(service.findById(id))

        return ResponseEntity<CharacterDto>(entityFound, HttpStatus.OK)
    }

    @GetMapping()
    fun findAll(): ResponseEntity<Collection<CharacterDto>> {
        val characterCollection = service.findAll()
        val entityCollection: Collection<CharacterDto> = characterCollection.map { Mapper.convert(it) }

        return ResponseEntity<Collection<CharacterDto>>(entityCollection, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<CharacterDto> {
        val entityDeleted: CharacterDto = Mapper.convert(service.delete(id))

        return ResponseEntity<CharacterDto>(entityDeleted, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody characterDto: CharacterDto): ResponseEntity<CharacterDto> {
        characterDto.id = id
        val character: Character = Mapper.convert(characterDto)

        val entityUpdated: CharacterDto = Mapper.convert(service.update(character))

        return ResponseEntity<CharacterDto>(entityUpdated, HttpStatus.OK)
    }

}