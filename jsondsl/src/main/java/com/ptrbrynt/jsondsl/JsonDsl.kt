package com.ptrbrynt.jsondsl

import com.google.gson.*

/**
 * Creates a [JsonArray], calls the provided function on it, and returns it
 */
fun jsonObject(init: JsonObject.() -> Unit): JsonObject {
    val jsonObject = JsonObject()
    jsonObject.init()
    return jsonObject
}

/**
 * Returns a [JsonArray] with the elements provided.
 *
 * Items provided must either be [Boolean], [Char], [String], [Number], or [JsonElement]
 */
fun jsonArrayOf(vararg item: Any?): JsonArray {
    val jsonArray = JsonArray()
    item.forEach {
        when (it) {
            is Boolean -> jsonArray.add(it)
            is Char -> jsonArray.add(it)
            is String -> jsonArray.add(it)
            is Number -> jsonArray.add(it)
            is JsonElement -> jsonArray.add(it)
            null -> jsonArray.add(JsonNull.INSTANCE)
            else -> jsonArray.add(it.toString())
        }
    }
    return jsonArray
}

/**
 * Adds a property to the [JsonObject] with the given [name] and [value].
 */
fun JsonObject.property(name: String, value: Any?) {
    when (value) {
        is Char -> addProperty(name, value)
        is Number -> addProperty(name, value)
        is String -> addProperty(name, value)
        is Boolean -> addProperty(name, value)
        is JsonElement -> add(name, value)
        null -> add(name, JsonNull.INSTANCE)
        else -> addProperty(name, value.toString())
    }
}

/**
 * Adds a [JsonObject] as a nested property to a [JsonObject]
 */
fun JsonObject.nestedObject(name: String, init: JsonObject.() -> Unit) {
    val jsonObject = JsonObject()
    jsonObject.init()
    add(name, jsonObject)
}

/**
 * Adds a [JsonArray] as a nested property to a [JsonObject]
 */
fun JsonObject.nestedArray(name: String, jsonArray: JsonArray) {
    add(name, jsonArray)
}