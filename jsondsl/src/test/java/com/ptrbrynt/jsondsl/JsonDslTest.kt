package com.ptrbrynt.jsondsl

import com.google.gson.JsonArray
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Test


class JsonDslTest {

    @Test
    fun basicJsonObject() {
        val json: JsonObject = jsonObject {}

        assertEquals(JsonObject(), json)
    }

    @Test
    fun basicJsonArray() {
        val json: JsonArray = jsonArrayOf()

        assertEquals(JsonArray(), json)
    }

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

    @Test
    fun jsonObject_WithNestedObject() {
        val json = jsonObject {
            nestedObject("object") {
                property("boolean", true)
            }
        }

        val expected = JsonObject().apply {
            add("object", JsonObject().apply {
                addProperty("boolean", true)
            })
        }

        assertEquals(expected, json)
    }

    @Test
    fun jsonObject_WithNestedArray() {
        val json = jsonObject {
            nestedArray("array", jsonArrayOf(true, "Hello"))
        }

        val expected = JsonObject().apply {
            add("array", JsonArray().apply {
                add(true)
                add("Hello")
            })
        }

        assertEquals(expected, json)
    }

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
                null
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
        }

        System.out.println(expected.toString())

        assertEquals(expected, json)
    }

    @Test(expected = IllegalArgumentException::class)
    fun jsonArray_WithIllegalArguments() {
        jsonArrayOf(emptyList<Long>())
    }
}