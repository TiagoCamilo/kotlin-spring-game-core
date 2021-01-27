package com.braveplayers.game.controllers

import com.braveplayers.game.dtos.CharacterDto
import com.braveplayers.game.dtos.GuildCharacterDto
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
    fun create(@Valid @RequestBody dto: CharacterDto): ResponseEntity<CharacterDto> {
        val mappedEntity: Character = Mapper.convert(dto)
        val createdEntity: CharacterDto = Mapper.convert(service.create(mappedEntity))

        return ResponseEntity<CharacterDto>(createdEntity, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<GuildCharacterDto> {
        val foundEntity: GuildCharacterDto = Mapper.convert(service.findById(id))

        return ResponseEntity<GuildCharacterDto>(foundEntity, HttpStatus.OK)
    }

    @GetMapping()
    fun findAll(): ResponseEntity<Collection<CharacterDto>> {
        val entityCollection = service.findAll()
        val dtoCollection: Collection<CharacterDto> = entityCollection.map { Mapper.convert(it) }

        return ResponseEntity<Collection<CharacterDto>>(dtoCollection, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<CharacterDto> {
        val deletedEntity: CharacterDto = Mapper.convert(service.delete(id))

        return ResponseEntity<CharacterDto>(deletedEntity, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: CharacterDto): ResponseEntity<CharacterDto> {
        dto.id = id
        val mappedEntity: Character = Mapper.convert(dto)
        val updatedEntity: CharacterDto = Mapper.convert(service.update(mappedEntity))

        return ResponseEntity<CharacterDto>(updatedEntity, HttpStatus.OK)
    }
}