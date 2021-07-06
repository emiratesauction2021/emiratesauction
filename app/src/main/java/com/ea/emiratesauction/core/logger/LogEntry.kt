package com.ea.emiratesauction.core.logger

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LogNotTimber")
fun Any.printMessage(
    messageTxt: String? = "",
    messageObj: Any? = null,
    tag: String? = "",
    type: LogType? = LogType.v,
    emojiUnicode: Int = 0,
    emoji: Emojis = Emojis.Happy
) {

    val builder = StringBuilder()
    val _tag = setTagStyle(tag, messageTxt, builder, messageObj)

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
@SuppressLint("LogNotTimber")
private fun setDebugMessage(
    _tag: String,
    builder: StringBuilder,
    emojiUnicode: Int,
    emoji: Emojis
) {
    Log.v(
        _tag,
        "-----------------${getEmoji(Emojis.Fire.unicode)}EmiratesAuction${getEmoji(Emojis.Fire.unicode)}-----------------: : "
    )
    Log.v(_tag, "   $builder ${getEmoji(emojiUnicode)}")
    Log.v(
        _tag,
        "-----------------${getEmoji(Emojis.Fire.unicode)}EmiratesAuction${getEmoji(Emojis.Fire.unicode)}-----------------: : "
    )
}

private fun checkLoggerIsEmpty(builder: StringBuilder) {
    if (builder.toString().trim().isEmpty())
        builder.append("Welcome to EA Logger")
}

private fun Any.setTagStyle(
    tag: String?,
    messageTxt: String?,
    builder: StringBuilder,
    messageObj: Any?
): String {
    var _tag = if (!tag.isNullOrEmpty()) tag else "EMIRATES_AUC"
    messageTxt?.let { builder.append(it) }
    _tag = "${getEmoji(Emojis.Happy.unicode)}${this.ClassName}" +
            "${getEmoji(Emojis.Happy.unicode)}$_tag" +
            "${getEmoji(Emojis.Sad.unicode)} "
    messageObj?.let { builder.append(it.toString()) }
    return _tag
}

val Any.ClassName: String
    get() = this::class.java.simpleName

