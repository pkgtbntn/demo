package com.example.demoapp.utils.ext

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.sql.Time
import java.util.*

/**
 * Forged by edryn on 3/21/19.
 */

/**
 * Get argument from bundle using [key]
 */
fun <T : Any> Fragment.argument(key: String) =
	lazy { arguments?.get(key) as? T ?: error("Intent Argument $key is missing") }

/**
 * Get argument from bundle using [key] with [defaultValue]
 */
fun <T : Any> Fragment.argument(key: String, defaultValue: T? = null) =
	lazy { arguments?.get(key) as? T ?: defaultValue }
