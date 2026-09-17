package com.ezorder.app

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Placeholder to confirm the instrumented test source set is wired up
 * and runs on a device/emulator. Real Compose UI tests for screens
 * start arriving from Phase 3 onward.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ezorder.app", appContext.packageName)
    }
}
