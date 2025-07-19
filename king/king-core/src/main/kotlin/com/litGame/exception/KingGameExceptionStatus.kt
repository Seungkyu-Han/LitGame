package com.litGame.exception

enum class KingGameExceptionStatus(
    val status: Int,
    val message: String
) {

    GAME_NOT_EXIST(1, "해당 게임이 존재하지 않습니다."),
    ROOM_IS_FULL(2, "참여 가능한 인원을 초과합니다.")

}