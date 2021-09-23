package com.example.demoapp.utils.ext

import com.google.gson.GsonBuilder

/**
 * Convert an Object to pretty json string
 */
fun Any.toPrettyJson(): String = GsonBuilder().setPrettyPrinting().create().toJson(this)