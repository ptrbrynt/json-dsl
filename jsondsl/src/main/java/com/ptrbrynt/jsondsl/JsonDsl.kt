package com.ptrbrynt.jsondsl

import com.google.gson.*

/**
 * Creates a [JsonArray], calls the provided function on it, and returns it
 */
fun jsonObject(init: JsonObjectBuilder.() -> Unit): JsonObject {
    val builder = JsonObjectBuilder()
    builder.init()
    return builder.build()
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
 * Converts a [List] to a [JsonArray]
 */
fun List<*>.toJsonArray(): JsonArray {
    val jsonArray = JsonArray()
    this.forEach {
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
 * Alternative syntax for [List].[toJsonArray]. Converts the [List] into a [JsonArray].
 */
fun jsonArrayOf(list: List<*>) = list.toJsonArray()