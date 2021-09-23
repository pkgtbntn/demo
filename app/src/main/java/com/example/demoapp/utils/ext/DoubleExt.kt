package com.example.demoapp.utils.ext

/**
 * Format a double to set a number of [digits]
 * to the right of decimal point
 */
fun Double.format(digits: Int): String = String.format("%.${digits}f", this)

/**
 * Get the whole number of a double
 */
fun Double.toWholeNumber() = toString().split(".").first().toInt()
