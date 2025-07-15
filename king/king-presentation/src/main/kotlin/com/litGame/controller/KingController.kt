package com.litGame.controller

import com.litGame.dto.res.CreateGameDto
import com.litGame.service.KingService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/king")
@Tag(name = "왕게임")
class KingController(
    private val kingService: KingService,
) {

    @PostMapping("/create-game")
    @Operation(description = "왕게임의 방을 생성하는 API", summary = "왕게임 방 만들기")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "방이 생성됨")
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun createGame(): CreateGameDto = CreateGameDto(gameRoomId = kingService.createGameRoom())
}