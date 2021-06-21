package com.ea.emiratesauction.core.extensions

inline fun <reified T : Enum<T>> String.asEnum(defaultValue: T): T =
        enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) } ?: defaultValue