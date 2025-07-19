package com.litGame.dto.res

import com.litGame.exception.KingGameExceptionStatus

data class GameErrorRes(
    val status: Int,
    val message: String
){
    constructor(status: KingGameExceptionStatus) : this(status.status, status.message)
}