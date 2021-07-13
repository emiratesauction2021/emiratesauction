package com.ea.emiratesauction.core.logger

import com.ea.emiratesauction.core.common.base.domain.BaseUseCase
import com.ea.emiratesauction.core.constants.network.parameters.NetworkRequestParametersType
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.usecase.ReportingUseCase
import com.ea.emiratesauction.features.test_toBeDeleted.network.requestTarget.PopularPeoplesRequestTarget
import kotlinx.coroutines.flow.collect


class LoggingManager {

    companion object {
        var loggerConfig = LoggerConfig(tag = "TestTage")
        fun error(
            message: Any? = null, tag: String? = "",
            emojiUnicode: Int = 0,
            emoji: Emojis = Emojis.Happy,
        ) {
            PrintUtil.printMessage(
                message = message,
                tag = tag,
                type = LogType.e,
                emojiUnicode = emojiUnicode,
                emoji = emoji
            )
        }

        suspend fun report(
            message: Any? = null, tag: String? = "",
            emojiUnicode: Int = 0,
            emoji: Emojis = Emojis.Happy,
            useCase: BaseUseCase<Any, Any>? = null
        ) {
            val map = mutableMapOf<String, String>()
            map["test"] = "asasasa"
            useCase?.execute(PopularPeoplesRequestTarget().apply {
                this.parameters = NetworkRequestParametersType.Standard(
                    map
                )
            })?.collect {
                when (it) {
                    is RequestResult.Success<*> -> {
                        log.debug(tag = "testUserCase0", message = it.value)
                    }

                    is RequestResult.Fail<*> -> {
                        log.debug(tag = "testUserCase1", message = it.networkError)
                    }
                }

            }
        }

        fun debug(
            message: Any? = null, tag: String? = "",
            emojiUnicode: Int = 0,
            emoji: Emojis = Emojis.Happy,
        ) {
            PrintUtil.printMessage(
                message = message,
                tag = tag,
                type = LogType.d,
                emojiUnicode = emojiUnicode,
                emoji = emoji
            )
        }

        fun warning(
            message: Any? = null, tag: String? = "",
            emojiUnicode: Int = 0,
            emoji: Emojis = Emojis.Happy,
        ) {
            PrintUtil.printMessage(
                message = message,
                tag = tag,
                type = LogType.w,
                emojiUnicode = emojiUnicode,
                emoji = emoji
            )
        }

        fun info(
            message: Any? = null, tag: String? = "",
            emojiUnicode: Int = 0,
            emoji: Emojis = Emojis.Happy,
        ) {
            PrintUtil.printMessage(
                message = message,
                tag = tag,
                type = LogType.i,
                emojiUnicode = emojiUnicode,
                emoji = emoji
            )
        }
    }
}

typealias log = LoggingManager