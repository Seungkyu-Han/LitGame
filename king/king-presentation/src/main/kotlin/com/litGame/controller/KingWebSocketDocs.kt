package com.litGame.controller

import com.litGame.dto.req.PlayGameDto
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
    @Operation(summary = "게임방 구독", description = "{'message': 'a'}의 형식입니다..."    )
    @Parameters(
        Parameter(name = "roomId", description = "구독할 방의 번호"),
    )
    @ApiResponses(
        ApiResponse(description = "메세지 응답 DTO", content = [Content(schema = Schema(implementation = MessageRes::class))])
    )
    fun subscribe(@PathVariable roomId: String, @RequestBody playerDto: PlayGameDto) {}

    @PostMapping("/pub/{roomId}")
    @Operation(summary = "게임방 이벤트 전송", description = "{'message': 'a'}의 형식입니다...")
    @Parameters(
        Parameter(name = "roomId", description = "이벤트를 전송할 방의 번호")
    )
    fun publish(@PathVariable roomId: String, @RequestBody playerDto: PlayGameDto){}
}