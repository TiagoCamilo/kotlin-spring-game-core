package com.braveplayers.game.controllers

import com.braveplayers.game.dtos.GuildDto
import com.braveplayers.game.entities.Guild
import com.braveplayers.game.services.GuildService
import com.braveplayers.game.util.Mapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/guilds")
class GuildController(private val service: GuildService) {

    @PostMapping
    fun create(@Valid @RequestBody dto: GuildDto): ResponseEntity<GuildDto> {
        val mappedEntity: Guild = Mapper.convert(dto)
        val createdEntity: GuildDto = Mapper.convert(service.create(mappedEntity))

        return ResponseEntity<GuildDto>(createdEntity, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<GuildDto> {
        val modelEntity: com.braveplayers.game.models.Guild = Mapper.convert(service.findById(id))

        println(modelEntity.name)
        modelEntity.characters.forEach {
            println(it.name)
            println(it.guild?.name)
        }

        val foundEntity: GuildDto = Mapper.convert(modelEntity)

        return ResponseEntity<GuildDto>(foundEntity, HttpStatus.OK)
    }

    @GetMapping()
    fun findAll(): ResponseEntity<Collection<GuildDto>> {
        val entityCollection = service.findAll()
        val dtoCollection: Collection<GuildDto> = entityCollection.map { Mapper.convert(it) }

        return ResponseEntity<Collection<GuildDto>>(dtoCollection, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<GuildDto> {
        val deletedEntity: GuildDto = Mapper.convert(service.delete(id))

        return ResponseEntity<GuildDto>(deletedEntity, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: GuildDto): ResponseEntity<GuildDto> {
        dto.id = id
        val mappedEntity: Guild = Mapper.convert(dto)
        val updatedEntity: GuildDto = Mapper.convert(service.update(mappedEntity))

        return ResponseEntity<GuildDto>(updatedEntity, HttpStatus.OK)
    }
}