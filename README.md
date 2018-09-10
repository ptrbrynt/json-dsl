# JSON DSL

[![Release](https://jitpack.io/v/ptrbrynt/json-dsl.svg)](https://jitpack.io/#ptrbrynt/json-dsl)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/cd498007c12b4e758b3a72dcda72211c)](https://www.codacy.com/app/ptrbrynt/json-dsl?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ptrbrynt/json-dsl&amp;utm_campaign=Badge_Grade)
[![GitHub license](https://img.shields.io/github/license/ptrbrynt/json-dsl.svg)](https://github.com/ptrbrynt/json-dsl/blob/master/LICENSE)
[![CircleCI (all branches)](https://img.shields.io/circleci/project/github/ptrbrynt/json-dsl.svg)](https://circleci.com/gh/ptrbrynt/json-dsl)


A type-safe builder for creating JSON objects and arrays in Kotlin.

## Installation

Firstly, add the JitPack repository to your project-level `build.gradle` file:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then, in your module's `build.gradle` file, add the dependency:
```groovy
dependencies {
    implementation 'com.github.ptrbrynt:json-dsl:0.1'
}
```

## Usage

To create an empty `JsonObject`, use this syntax:

```kotlin
val json: JsonObject = jsonObject { }
```

Add properties like this:

```kotlin
val json: JsonObject = jsonObject {
    property("my_property", 7)
    property("another_one", "hello")
}
```

JSON DSL supports `Boolean`, `String`, `Char`, and `Number` properties.

You can add a nested object like this:

```kotlin
val json: JsonObject = jsonObject {
    nestedObject {
        property("hello", 7)
    }
}
```

You can create a `JsonArray` like this:
```kotlin
val array: JsonArray = jsonArrayOf(
    7, 
    "hello",
    jsonObject {
        property("a_property", 'a')
    },
    jsonArrayOf(
        2,
        null
    )
)
```

You can add a `JsonArray` to a `JsonObject` like this:

```kotlin
val obj: JsonObject = jsonObject {
    nestedArray("array", jsonArrayOf(true, "hello"))
}
```

