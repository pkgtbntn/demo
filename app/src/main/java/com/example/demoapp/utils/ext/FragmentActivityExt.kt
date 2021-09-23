package com.example.demoapp.utils.ext

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.fondesa.kpermissions.extension.*
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.newTask
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

/**
 * Get argument from intent bundle using [key]
 */
//fun <T : Any> FragmentActivity.argument(key: String) =
//	lazy { intent.extras[key] as? T ?: error("Intent Argument $key is missing") }
//
///**
// * Get argument from intent bundle using [key] with [defaultValue]
// */
//fun <T : Any> FragmentActivity.argument(key: String, defaultValue: T? = null) =
//	lazy { intent.extras[key] as? T ?: defaultValue }
