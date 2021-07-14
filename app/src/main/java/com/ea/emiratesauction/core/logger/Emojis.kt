package com.ea.emiratesauction.core.logger

enum class Emojis(val unicode: Int) {
    Happy(0x1F60A),
    Sad(0x1F609),
    Hope(0x1F648),
    Animal(0x1F434),
    Fire(0x1F525),
}
fun getEmoji(code: Int) = String(Character.toChars(code))
