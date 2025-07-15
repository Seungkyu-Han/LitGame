package com.litGame.dto.res

import io.swagger.v3.oas.annotations.media.Schema

data class CreateGameDto(
    @Schema(name = "게임 방의 번호", example = "1204")
    val gameRoomId: Int
)
