package com.mmk.common.ui.util.errorhandling

import com.mmk.common.ui.util.UiMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface UiMessageHandler : UiMessageOwner {
    suspend fun emitMessage(uiMessage: UiMessage)
}

class UiMessageHandlerImpl : UiMessageHandler {
    private val _uiMessage = MutableSharedFlow<UiMessage?>()
    override val uiMessage: SharedFlow<UiMessage?>
        get() = _uiMessage.asSharedFlow()

    override suspend fun emitMessage(uiMessage: UiMessage) {
        _uiMessage.emit(uiMessage)
    }
}
