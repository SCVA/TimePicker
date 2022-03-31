package com.develou.timepicker_en_android

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `parsear tiempo desde string`() {
        val expected = LocalTime.of(22, 0)

        val stringTime = "10:00 p. m."
        val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.getDefault())
        val actual = LocalTime.parse(stringTime, formatter)

        assertEquals(expected, actual)
    }
}