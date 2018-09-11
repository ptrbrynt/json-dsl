package com.ptrbrynt.jsondsl

import com.google.gson.JsonArray
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests JSON DSL API
 */
class JsonDslTest {

    /**
     * Tests that the [jsonObject] function creates a [JsonObject]
     */
    @Test
    fun basicJsonObject() {
        val json: JsonObject = jsonObject {}

        assertEquals(JsonObject(), json)
    }

    /**
     * Tests that the [jsonArrayOf] function creates a [JsonArray]
     */
    @Test
    fun basicJsonArray() {
        val json: JsonArray = jsonArrayOf()

        assertEquals(JsonArray(), json)
    }

    /**
     * Tests creation of a [JsonObject] with a [Char] property
     */
    @Test
    fun jsonObject_WithCharProperty() {
        val json = jsonObject {
            property("char", 'a')
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
            property("number", 7)
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
            property("string", "Hello")
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
            property("boolean", true)
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
            property("null", null)
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
            property("object", jsonObject {
                property("boolean", true)
            })
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
            property("array", jsonArrayOf(true, "Hello"))
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
            property("complex", object {
                val thing = "A Thing"
                val somethingElse = "Hi"
                override fun toString() = "$thing - $somethingElse"
            })
        }

        val expected = JsonObject().apply {
            addProperty("complex", "A Thing - Hi")
        }

        assertEquals(expected, json)
    }

    /**
     * Tests the creation of a [JsonArray] with a variety of elements
     */
    @Test
    fun jsonArray_WithProperties() {
        val json = jsonArrayOf(
                true,
                "Hello",
                7.2,
                'a',
                jsonObject {
                    property("boolean", false)
                },
                jsonArrayOf(
                        19423,
                        18432
                ),
                null,
                object {
                    val item = "Hello"
                    val another = "Goodbye"

                    override fun toString() = "$item: $another"
                }
        )

        val expected = JsonArray().apply {
            add(true)
            add("Hello")
            add(7.2)
            add('a')
            add(JsonObject().apply {
                addProperty("boolean", false)
            })
            add(JsonArray().apply {
                add(19423)
                add(18432)
            })
            add(JsonNull.INSTANCE)
            add("Hello: Goodbye")
        }

        assertEquals(expected, json)
    }
}