package com.ea.emiratesauction.core.logger

import kotlinx.coroutines.flow.MutableStateFlow

data class LoggerConfig(
    val tag: String = "ArabianSystemsLogger",
    var isLoggingEnabled: MutableStateFlow<Boolean> = MutableStateFlow(true)
)