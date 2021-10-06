package com.example.demoapp.utils.ext

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.demoapp.R
import com.google.android.material.snackbar.Snackbar
import java.io.File

/**
 * Check if internet is available
 * returns [Boolean]
 */
//fun Context.isInternetAvailable() = connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting == true

/**
 * Handle connection availability
 * @param available
 * @param unavailable
 */
//fun Context.internetConnectivityHandler(available: (() -> Unit), unavailable: (() -> Unit)? = null) {
//	if (isInternetAvailable()) available.invoke() else unavailable?.invoke()
//}

/**
 * Get color resource
 */
fun Context.getCompatColor(colorRes: Int) = ContextCompat.getColor(this, colorRes)

/**
 * Get drawable resource
 */
fun Context.getCompatDrawable(drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)


//enum class AlertDialogType(val symbol: Char, val color: Int) {
//	INFO('i', R.color.status_info),
//	CONFIRMATION('?', R.color.status_confirmation),
//	WARNING('!', R.color.status_warning),
//	ERROR('!', R.color.status_error),
//	SUCCESS('✓', R.color.status_success),
//	DEFAULT(Char.MIN_VALUE, R.color.status_default)
//}

/**
 * Base function for alert dialogs
 */
//fun Context.showAlertDialog(
//	rootView: ViewGroup,
//	alertType: AlertDialogType,
//	title: String,
//	message: String,
//	positiveButton: ButtonSpecs?,
//	negativeButton: ButtonSpecs?,
//	isCancelable: Boolean? = false,
//	dark: Boolean? = false) {
//
//	// init dialog and view
//	val dialog = AlertDialog.Builder(this).create()
//	val dialogView = if (dark == true) rootView.inflate(
//		R.layout.dialog_alert_dark,
//		false) else rootView.inflate(R.layout.dialog_alert, false)
//	// setup view
//	dialogView.apply {
//		// texts
//		if (alertType == AlertDialogType.DEFAULT) {
//			dialert_text_symbol.text = ""
//		} else {
//			dialert_text_symbol.apply {
//				with(alertType) {
//					text = symbol.toString()
//					setTextColor(getCompatColor(color))
//				}
//			}
//		}
//		dialert_text_title.text = title
//		dialert_text_message.text = message
//		// positive button
//		dialert_button_positive.apply {
//			text = positiveButton?.text ?: "OK"
//			setTextColor(getCompatColor(alertType.color))
//			setOnClickListener {
//				positiveButton?.handler?.invoke()
//				dialog.dismiss()
//			}
//		}
//		// negative button
//		negativeButton?.let {
//			dialert_button_negative.apply {
//				text = negativeButton.text ?: "CANCEL"
//				setOnClickListener {
//					negativeButton.handler?.invoke()
//					dialog.dismiss()
//				}
//			}
//		} ?: dialert_button_negative.hide()
//	}
//	// setup AlertDialog
//	dialog.apply {
//		setCancelable(isCancelable ?: true)
//		setView(dialogView)
//		show()
//	}
//}

enum class SnackbarType {
	DEFAULT,
	SUCCESS,
	ERROR
}

/**
 * Base function for snackbars
 */
fun Context.showSnackbar(
	rootView: ViewGroup,
	length: Int,
	type: SnackbarType? = null,
	message: String,
	actionText: String? = null,
	actionHandler: (() -> Unit)? = null,
	@ColorRes textColor: Int? = null,
	@ColorRes backgroundColor: Int? = null) {
	// init
	val snackbar = Snackbar.make(rootView, message, length)
	// custom actionText
	snackbar.view.findViewById<TextView>(R.id.snackbar_text).apply {
		// set font
		typeface = ResourcesCompat.getFont(this@showSnackbar, R.font.qanelassoft_regular)
		// set line spacing
		setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics), 1f)
		// color
		textColor?.let { setTextColor(getCompatColor(it)) }
		// center actionText
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) textAlignment = View.TEXT_ALIGNMENT_CENTER
		else gravity = Gravity.CENTER_HORIZONTAL
	}
	// custom actionHandler button
	snackbar.view.findViewById<TextView>(R.id.snackbar_action).apply {
		// set font
		typeface = ResourcesCompat.getFont(this@showSnackbar, R.font.qanelassoft_extrabold)
	}
	// custom snackbar
	snackbar.apply {
		// background
		view.setBackgroundColor(
			ContextCompat.getColor(
				this@showSnackbar,
				when (type) {
					SnackbarType.DEFAULT -> R.color.status_blank
					SnackbarType.SUCCESS -> R.color.status_success
					SnackbarType.ERROR -> R.color.status_error
					else -> backgroundColor ?: R.color.status_blank
				}))
		// actionHandler actionText color
		setActionTextColor(getCompatColor(android.R.color.white))
		// actionHandler
		actionText?.let { text ->
			actionHandler?.let { handler ->
				setAction(text) { handler.invoke() }
			}
		}
		show()
	}
}

/** App Data **/

//fun Context.clearAppData() {
//	val cache = cacheDir
//	val appDir = File(cache.parent)
//	if (appDir.exists()) {
//		val children = appDir.list()
//		for (d in children) {
//			if (d != "lib") {
//				deleteDirectory(File(appDir, d))
//				e("File /data/data/APP_PACKAGE/$d DELETED")
//			}
//		}
//	}
//}

fun Context.deleteDirectory(dir: File): Boolean {
	if (dir.isDirectory) {
		for (i in dir.list().indices) {
			val success = try {
				deleteDirectory(File(dir, dir.list()[i]))
			} catch (e: ArrayIndexOutOfBoundsException) {
				false
			}
			if (!success) {
				return false
			}
		}
	}
	return dir.delete()
}
