package com.ptrbrynt.jsondsl

import com.google.gson.JsonArray
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Test

class JsonArrayTest {

    /**
     * Tests that the [jsonArrayOf] function creates a [JsonArray]
     */
    @Test
    fun basicJsonArray() {
        val json: JsonArray = jsonArrayOf()

        assertEquals(JsonArray(), json)
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
                    "boolean" to false
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

    /**
     * Tests conversion of [List] to [JsonArray] using [toJsonArray]
     */
    @Test
    fun list_toJsonArray() {
        val list = listOf("hello", 7, 'a', null, true, Pair(7, "hello"), jsonObject { "thing" to false })
        val json = list.toJsonArray()

        val expected = JsonArray().apply {
            add("hello")
            add(7)
            add('a')
            add(JsonNull.INSTANCE)
            add(true)
            add("(7, hello)")
            add(JsonObject().apply {
                addProperty("thing", false)
            })
        }

        assertEquals(expected, json)
    }

    /**
     * Tests alternative sytax for conversion of [List] to [JsonArray] using [jsonArrayOf]
     */
    @Test
    fun list_toJsonArray_Alt() {
        val list = listOf("hello", 7, 'a', null, true, Pair(8, 2), jsonObject { "thing" to true })
        val json = jsonArrayOf(list)

        val expected = JsonArray().apply {
            add("hello")
            add(7)
            add('a')
            add(JsonNull.INSTANCE)
            add(true)
            add("(8, 2)")
            add(JsonObject().apply {
                addProperty("thing", true)
            })
        }

        assertEquals(expected, json)
    }

}