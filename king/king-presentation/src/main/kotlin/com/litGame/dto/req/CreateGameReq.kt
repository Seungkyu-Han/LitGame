package com.litGame.dto.req

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "게임 생성을 요청하는 DTO")
data class CreateGameReq(

    @Schema(name = "게임 방의 이름", example = "게임 1234", required = false, description = "NULL을 입력하면 자동으로 생성됩니다.")
    val name: String?,

    @Schema(name = "수용 가능 인원", required = true)
    val capacity: Int
)
