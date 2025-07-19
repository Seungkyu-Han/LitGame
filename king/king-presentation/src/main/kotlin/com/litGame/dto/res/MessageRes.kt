package com.litGame.dto.res

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "메시지 DTO", description = "소켓 통신 중에 발생하는 에러를 던집니다.")
data class MessageRes(
    @Schema(name = "전송자 ID")
    val sender: String,
    @Schema(name = "메시지 내용")
    val content: String,
)
