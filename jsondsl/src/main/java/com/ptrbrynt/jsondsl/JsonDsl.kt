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
        if (!(it is Boolean || it is Char || it is String || it is Number || it is JsonElement || it == null)) {
            throw IllegalArgumentException("Items must be a boolean, char, string, number, or JsonElement.")
        }
        when (it) {
            is Boolean -> jsonArray.add(it)
            is Char -> jsonArray.add(it)
            is String -> jsonArray.add(it)
            is Number -> jsonArray.add(it)
            is JsonElement -> jsonArray.add(it)
            null -> jsonArray.add(JsonNull.INSTANCE)
        }
    }
    return jsonArray
}

/**
 * Adds a [Char] property to a [JsonObject]
 */
fun JsonObject.property(name: String, value: Char?) {
    addProperty(name, value)
}

/**
 * Adds a [Number] property to a [JsonObject]
 */
fun JsonObject.property(name: String, value: Number?) {
    addProperty(name, value)
}

/**
 * Adds a [String] property to a [JsonObject]
 */
fun JsonObject.property(name: String, value: String?) {
    addProperty(name, value)
}

/**
 * Adds a [Boolean] property to a [JsonObject]
 */
fun JsonObject.property(name: String, value: Boolean?) {
    addProperty(name, value)
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