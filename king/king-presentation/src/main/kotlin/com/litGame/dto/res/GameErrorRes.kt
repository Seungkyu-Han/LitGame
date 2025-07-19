package com.litGame.dto.res

import com.litGame.exception.KingGameExceptionStatus
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "게임 에러 DTO", description = "소켓 통신 중에 발생하는 에러를 던집니다.")
data class GameErrorRes(
    @Schema(description = "해당 에러의 상태(한승규에게 문의해주세요)")
    val status: Int,
    @Schema(description = "해당 에러의 메시지")
    val message: String
){
    constructor(status: KingGameExceptionStatus) : this(status.status, status.message)
}