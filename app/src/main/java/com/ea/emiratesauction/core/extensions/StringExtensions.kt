package com.ea.emiratesauction.core.extensions

/**
 * Retrieve the enum value of a specific item or sends back the default value if it fails to parse the item to the enum
 */
inline fun <reified T : Enum<T>> String.asEnum(defaultValue: T): T =
        enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) } ?: defaultValue