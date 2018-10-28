package net.iqbalfauzan.mykotlinapp.utils

import kotlinx.coroutines.experimental.Unconfined
import org.junit.Assert.*
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}