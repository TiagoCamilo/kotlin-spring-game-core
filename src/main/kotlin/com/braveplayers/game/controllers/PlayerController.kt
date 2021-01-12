package com.braveplayers.game.controllers

import com.braveplayers.game.dtos.PlayerDto
import com.braveplayers.game.entities.Player
import com.braveplayers.game.services.PlayerService
import com.braveplayers.game.util.Mapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/players")
class PlayerController(private val service: PlayerService) {

    @PostMapping
    fun create(@RequestBody playerDto: PlayerDto): PlayerDto {
        val player: Player = Mapper.convert(playerDto)
        return Mapper.convert(service.create(player))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): PlayerDto = Mapper.convert(service.findById(id))

    @GetMapping()
    fun findAll(): Collection<PlayerDto> {
        val playerCollection = service.findAll()
        return playerCollection.map { Mapper.convert(it) }
    }

}