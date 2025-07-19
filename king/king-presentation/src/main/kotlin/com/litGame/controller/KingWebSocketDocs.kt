package com.litGame.controller

import com.litGame.dto.req.PlayGameDto
import com.litGame.dto.res.GameErrorRes
import com.litGame.dto.res.MessageRes
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/websocket/v1/king")
@Tag(name = "왕게임 소켓 문서(REST API 사용 X)")
class KingWebSocketDocs {

    @PostMapping("/sub/{roomId}")
    @Operation(summary = "게임방 구독")
    @Parameters(
        Parameter(name = "roomId", description = "구독할 방의 번호"),
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "다른 사용자들이 응답받는 메시지의 DTO", content = [Content(schema = Schema(implementation = MessageRes::class))]),
        ApiResponse(responseCode = "201", description = "게임 중 혹은 게임 연결 중 발생하는 에러 DTO", content = [Content(schema = Schema(implementation = GameErrorRes::class))])
    )
    fun subscribe(@PathVariable roomId: String, @RequestBody playerDto: PlayGameDto) {}

    @PostMapping("/pub/{roomId}")
    @Operation(summary = "게임방 이벤트 전송")
    @Parameters(
        Parameter(name = "roomId", description = "이벤트를 전송할 방의 번호")
    )
    fun publish(@PathVariable roomId: String, @RequestBody playerDto: PlayGameDto){}
}