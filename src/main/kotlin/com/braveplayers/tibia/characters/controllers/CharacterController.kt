package com.braveplayers.tibia.characters.controllers

import com.braveplayers.tibia.characters.dtos.CharacterDto
import com.braveplayers.tibia.characters.services.CharacterService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/characters")
class CharacterController {

    private val service: CharacterService

    constructor(service: CharacterService){
        this.service = service
    }

    @PostMapping
    fun create(@RequestBody characterDto: CharacterDto): CharacterDto {
        return service.create(characterDto)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CharacterDto {
        return service.findById(id)
    }
}