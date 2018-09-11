package com.ptrbrynt.jsondsl

import com.google.gson.JsonArray
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests JSON DSL API
 */
class JsonBuilderTest {

    /**
     * Tests that the [jsonObject] function creates a [JsonObject]
     */
    @Test
    fun basicJsonObject() {
        val json: JsonObject = jsonObject {}

        assertEquals(JsonObject(), json)
    }

    /**
     * Tests creation of a [JsonObject] with a [Char] property
     */
    @Test
    fun jsonObject_WithCharProperty() {
        val json = jsonObject {
            "char" to 'a'
        }

        val expected = JsonObject().apply {
            addProperty("char", 'a')
        }

        assertEquals(expected, json)
    }

    /**
     * Tests creation of a [JsonObject] with a [Number] property
     */
    @Test
    fun jsonObject_WithNumberProperty() {
        val json = jsonObject {
            "number" to 7
        }

        val expected = JsonObject().apply {
            addProperty("number", 7)
        }

        assertEquals(expected, json)
    }

    /**
     * Tests creation of a [JsonObject] with a [String] property
     */
    @Test
    fun jsonObject_WithStringProperty() {
        val json = jsonObject {
            "string" to "Hello"
        }

        val expected = JsonObject().apply {
            addProperty("string", "Hello")
        }

        assertEquals(expected, json)
    }

    /**
     * Tests creation of a [JsonObject] with a [Boolean] property
     */
    @Test
    fun jsonObject_WithBooleanProperty() {
        val json = jsonObject {
            "boolean" to true
        }

        val expected = JsonObject().apply {
            addProperty("boolean", true)
        }

        assertEquals(expected, json)
    }

    /**
     * Tests creation of a [JsonObject] with a null property
     */
    @Test
    fun jsonObject_WithNullProperty() {
        val json = jsonObject {
            "null" to null
        }

        val expected = JsonObject().apply {
            add("null", JsonNull.INSTANCE)
        }

        assertEquals(expected, json)
    }

    /**
     * Tests creation of a [JsonObject] with a nested [JsonObject]
     */
    @Test
    fun jsonObject_WithNestedObject() {
        val json = jsonObject {
            "object" to jsonObject {
                "boolean" to true
            }
        }

        val expected = JsonObject().apply {
            add("object", JsonObject().apply {
                addProperty("boolean", true)
            })
        }

        assertEquals(expected, json)
    }

    /**
     * Tests creation of a [JsonObject] with a nested [JsonArray]
     */
    @Test
    fun jsonObject_WithNestedArray() {
        val json = jsonObject {
            "array" to jsonArrayOf(true, "Hello")
        }

        val expected = JsonObject().apply {
            add("array", JsonArray().apply {
                add(true)
                add("Hello")
            })
        }

        assertEquals(expected, json)
    }

    /**
     * Tests creation of a [JsonObject] with a complex property
     */
    @Test
    fun jsonObject_WithComplexProperty() {
        val json = jsonObject {
            "complex" to object {
                val thing = "A Thing"
                val somethingElse = "Hi"
                override fun toString() = "$thing - $somethingElse"
            }
        }

        val expected = JsonObject().apply {
            addProperty("complex", "A Thing - Hi")
        }

        assertEquals(expected, json)
    }


}