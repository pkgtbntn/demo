package com.example.demoapp.utils.ext

import android.widget.TextView

/**
 * Sanitize text
 */
fun TextView.text() = text.toString().trim()

