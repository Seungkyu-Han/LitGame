package com.litGame.dto.req

import com.litGame.enums.TypeEnum
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "소켓 메시지 DTO")
data class PlayGameDto(

    @Schema(
        description = "메시지의 종류",
        example = "MESSAGE",
        implementation = TypeEnum::class
    )
    val type: TypeEnum,

    @Schema(
        description = "메시지의 내용"
    )
    val content: String
)