package com.ea.emiratesauction.core.logger

import android.annotation.SuppressLint
import android.util.Log
import com.ea.emiratesauction.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoggingManager @Inject constructor() {
    companion object {
        var isLoggingEnabled = MutableStateFlow(true)
    }
}

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
fun Any.printMessage(
    message: Any? = null,
    tag: String? = "",
    type: LogType? = LogType.v,
    emojiUnicode: Int = 0,
    emoji: Emojis = Emojis.Happy
) {
    CoroutineScope(Main + Job()).launch {
        LoggingManager.isLoggingEnabled.collect {
            when (it) {
                true -> {
                    if (BuildConfig.FLAVOR == "Dev") {
                        val builder = StringBuilder()
                        val _tag = setTagStyle(tag, builder, message)
                        checkLoggerIsEmpty(builder)
                        when (type) {
                            LogType.v -> {
                                setDebugMessage(_tag, builder, emojiUnicode, emoji)
                            }
                            else -> {
                            }
                            /*LogType.w -> {
                                Log.w(_tag, "-----------------EmiratesAuction-----------------: : ")
                                Log.w(_tag, "$builder")
                                Log.w(_tag, "-----------------EmiratesAuction-----------------: : ")
                            }
                            LogType.d -> {
                                Log.d(_tag, "-----------------EmiratesAuction-----------------: : ")
                                Log.d(_tag, "$builder")
                                Log.d(_tag, "-----------------EmiratesAuction-----------------: : ")
                            }
                            LogType.e -> {
                                Log.e(_tag, "-----------------EmiratesAuction-----------------: : ")
                                Log.e(_tag, "$builder")
                                Log.e(_tag, "-----------------EmiratesAuction-----------------: : ")
                            }
                            LogType.i -> {
                                Log.i(_tag, "-----------------EmiratesAuction-----------------: : ")
                                println()
                                Log.i(_tag, "$builder")
                                println()
                                Log.i(_tag, "-----------------EmiratesAuction-----------------: : ")
                            }*/

                        }
                    }
                }
                false -> {

                }
            }
        }
    }
}


@SuppressLint("LogNotTimber") fun setDebugMessage(
    _tag: String,
    builder: StringBuilder,
    emojiUnicode: Int,
    emoji: Emojis
) {
    Log.v(
        _tag,
        "-----------------${getEmoji(Emojis.Fire.unicode)}EmiratesAuction${getEmoji(Emojis.Fire.unicode)}-----------------: : "
    )
    Log.v(_tag, "   $builder ${Emojis.Fire}")
    Log.v(
        _tag,
        "-----------------${getEmoji(Emojis.Fire.unicode)}EmiratesAuction${getEmoji(Emojis.Fire.unicode)}-----------------: : "
    )
}

fun checkLoggerIsEmpty(builder: StringBuilder) {
    if (builder.toString().trim().isEmpty())
        builder.append("Welcome to EA Logger")
}

fun Any.setTagStyle(
    tag: String?,
    builder: StringBuilder,
    messageObj: Any?
): String {
    var _tag = if (!tag.isNullOrEmpty()) tag else "EMIRATES_AUC"
    _tag = "${getEmoji(Emojis.Happy.unicode)}${this.ClassName}" +
            "${getEmoji(Emojis.Happy.unicode)}$_tag" +
            "${getEmoji(Emojis.Sad.unicode)} "
    messageObj?.let { builder.append(it.toString()) }
    return _tag
}

val Any.ClassName: String
    get() = this::class.java.simpleName
