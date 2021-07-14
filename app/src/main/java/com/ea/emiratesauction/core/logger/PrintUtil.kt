package com.ea.emiratesauction.core.logger

import android.annotation.SuppressLint
import android.util.Log
import com.ea.emiratesauction.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@SuppressLint("LogNotTimber")
class PrintUtil() {
    companion object {

        /**
         * PrintMessage to print logging messages and object with custom format and tags
         * the message tag will be className then tage name then the message
         * @param message it's from type Any to print any king of objects[text,classes]
         * @param tag to name the printed object
         * @param type it defines the log [Debug,Verbose,Error,Info]
         * @param emojiUnicode to print emoji via unicode
         * @param emoji to print emoji from predefined emoji list
         *
         * all @params are optional
         * */
        @SuppressLint("LogNotTimber")
        fun printMessage(
            message: Any? = null,
            tag: String? = "",
            type: LogType? = LogType.v,
            emojiUnicode: Int = 0,
            emoji: Emojis = Emojis.Happy
        ) {
            CoroutineScope(Dispatchers.Main + Job()).launch {
                log.loggerConfig.isLoggingEnabled.collect {
                    when (it) {
                        true -> {
                            if (BuildConfig.FLAVOR == "Dev") {
                                val builder = StringBuilder()
                                val _tag = setTagStyle(tag, builder, message)
                                checkLoggerIsEmpty(builder)
                                when (type) {
                                    LogType.d ->
                                        setDebugLogg(_tag, builder, emojiUnicode, emoji)
                                    LogType.e ->
                                        setErrorLogg(_tag, builder, emojiUnicode, emoji)
                                    LogType.i ->
                                        setInfoLogg(_tag, builder, emojiUnicode, emoji)
                                    LogType.w ->
                                        setWarninigLogg(_tag, builder, emojiUnicode, emoji)
                                }
                            }
                        }
                    }
                }
            }
        }

        private fun checkLoggerIsEmpty(builder: StringBuilder) {
            if (builder.toString().trim().isEmpty())
                builder.append("Welcome to EA Logger")
        }

        private fun setTagStyle(
            tag: String?,
            builder: StringBuilder,
            messageObj: Any?
        ): String {
            var _tag = if (!tag.isNullOrEmpty()) tag else LoggingManager.loggerConfig.tag
            _tag = "${getEmoji(Emojis.Happy.unicode)}${this.ClassName}" +
                    "${getEmoji(Emojis.Happy.unicode)}$_tag" +
                    "${getEmoji(Emojis.Sad.unicode)} "
            messageObj?.let { builder.append(it.toString()) }
            return _tag
        }

        private fun setDebugLogg(
            _tag: String, builder: StringBuilder, emojiUnicode: Int, emoji: Emojis
        ) {
            Log.d(
                _tag,
                "__________________${getEmoji(Emojis.Fire.unicode)}" +
                        LoggingManager.loggerConfig.tag +
                        "${getEmoji(Emojis.Fire.unicode)}__________________"
            )
            Log.d(_tag, "   $builder ${Emojis.Fire}")
            Log.d(
                _tag,
                "__________________${getEmoji(Emojis.Fire.unicode)}" +
                        LoggingManager.loggerConfig.tag +
                        "${getEmoji(Emojis.Fire.unicode)}__________________"
            )
        }

        private fun setErrorLogg(
            _tag: String, builder: StringBuilder, emojiUnicode: Int, emoji: Emojis
        ) {
            Log.e(
                _tag,
                "__________________${getEmoji(Emojis.Fire.unicode)}" +
                        LoggingManager.loggerConfig.tag +
                        "${getEmoji(Emojis.Fire.unicode)}__________________"
            )
            Log.e(_tag, "   $builder ${Emojis.Fire}")
            Log.e(
                _tag,
                "__________________${getEmoji(Emojis.Fire.unicode)}" +
                        LoggingManager.loggerConfig.tag +
                        "${getEmoji(Emojis.Fire.unicode)}__________________"
            )
        }

        private fun setInfoLogg(
            _tag: String, builder: StringBuilder, emojiUnicode: Int, emoji: Emojis
        ) {
        }

        private fun setWarninigLogg(
            _tag: String, builder: StringBuilder, emojiUnicode: Int, emoji: Emojis
        ) {
        }
    }
}

val Any.ClassName: String
    get() = this::class.java.simpleName

