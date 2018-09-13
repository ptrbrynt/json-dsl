package com.ptrbrynt.jsondsl

import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests miscellaneous methods provided by the library
 */
class MiscTests {

    /**
     * Ensures that [HashMap] to [JsonObject] using [toJsonObject] works correctly.
     */
    @Test
    fun hashMapToJson() {

        val map = hashMapOf(
                Pair("property", 9),
                Pair("another_property", 724)
        )

        val expected = JsonObject().apply {
            addProperty("property", 9)
            addProperty("another_property", 724)
        }

        assertEquals(expected, map.toJsonObject())
    }

}