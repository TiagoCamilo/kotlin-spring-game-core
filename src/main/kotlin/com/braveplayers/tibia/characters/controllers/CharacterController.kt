package com.braveplayers.tibia.characters.controllers

import com.braveplayers.tibia.characters.dtos.CharacterDto
import com.braveplayers.tibia.characters.entities.Character
import com.braveplayers.tibia.characters.services.CharacterService
import com.braveplayers.tibia.characters.util.Mapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/characters")
class CharacterController(private val service: CharacterService) {

    @PostMapping
    fun create(@RequestBody characterDto: CharacterDto): CharacterDto {
        val character: Character = Mapper.convert(characterDto)
        return Mapper.convert(service.create(character))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CharacterDto = Mapper.convert(service.findById(id))

}