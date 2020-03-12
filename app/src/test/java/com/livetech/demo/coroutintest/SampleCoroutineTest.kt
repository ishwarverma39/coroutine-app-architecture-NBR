package com.livetech.demo.coroutintest

import com.livetech.demo.utils.CoroutineTestRule
import com.livtech.common.core.utils.DefaultDispatcherProvider
import com.livtech.common.core.utils.DispatcherProvider
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test
import kotlin.math.sqrt

@ExperimentalCoroutinesApi
class SampleCoroutineTest {
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Test
    fun useTestCoroutineDispatcherRunBlockingTest() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val heavyWorker = HeavyWorker(coroutinesTestRule.testDispatcherProvider)
            val expected = 666666671666
            val result = heavyWorker.heavyOperation()
            assertEquals(expected, result)
        }
}

class HeavyWorker(private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()) {
    suspend fun heavyOperation(): Long {
        return withContext(dispatchers.default()) {
            delay(30_000)
            return@withContext doHardMaths()
        }

    }

    private fun doHardMaths(): Long {
        var count = 0.0
        for (i in 1..100_000_000) {
            count += sqrt(i.toDouble())
        }
        return count.toLong()
    }
}
