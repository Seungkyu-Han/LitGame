package com.litGame.controller

import com.litGame.dto.req.CreateGameReq
import com.litGame.dto.res.CreateGameRes
import com.litGame.service.KingGameService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/king")
@Tag(name = "왕게임")
class KingController(
    private val kingGameService: KingGameService
) {

    @PostMapping("/create-game")
    @Operation(description = "왕게임의 방을 생성하는 API", summary = "왕게임 방 만들기")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "방이 생성됨")
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun createGame(@RequestBody createGameReq: CreateGameReq): CreateGameRes{
        return CreateGameRes(gameRoomId = kingGameService.createGame(
            name = createGameReq.name,
            capacity = createGameReq.capacity
        ))
    }

}