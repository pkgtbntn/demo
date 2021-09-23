package com.example.demoapp.utils.ext

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.fondesa.kpermissions.extension.*
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.sql.Time
import java.util.*

/**
 * Set Activity to fullscreen and status bar icons to black
 */
//@RequiresApi(Build.VERSION_CODES.M)
//fun Activity.setToFullScreenAndLightStatusBar() {
//	window.apply {
//		decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//		fromApi(23, true) { decorView.systemUiVisibility += View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR }
//		statusBarColor = Color.TRANSPARENT
//	}
//}

/**
 * Open [url] in browser
 */
fun Activity.openLinkInBrowser(url: String) {
	startActivity(
		Intent(
			Intent.ACTION_VIEW, Uri.parse(
				try {
					url.precedeLinkWithHttps()
				} catch (e: Exception) {
					url.precedeLinkWithHttp()
				}
			)))
}

/**
 * Open soft keyboard and focus [view]
 */
fun Activity.showSoftKeyboard(view: View? = null) {
	view?.requestFocus()
	val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
	imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

/**
 * Hide soft keyboard
 */
//fun Activity.hideSoftKeyboard() {
//	val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//	try {
//		inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
//	} catch (e: Exception) {
//		doNothing()
//	}
//}

/**
 * Hide keyboard when another uneditable view is touched
 */
fun Context.hidesSoftInputOnTouch(rootLayout: ViewGroup) {
	rootLayout.apply {
		isClickable = true
		isFocusable = true
		isFocusableInTouchMode = true
		setOnFocusChangeListener { v, hasFocus ->
			if (hasFocus) {
				val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
				imm.hideSoftInputFromWindow(v.rootView.windowToken, 0)
			}
		}
	}
}

/**
 * Listen for keyboard state changes
 */
fun Context.softInputStateChangeListener(viewGroup: ViewGroup, shown: () -> Unit, hidden: () -> Unit) {
	with(viewGroup) {
		viewTreeObserver.addOnGlobalLayoutListener {
			val r = Rect()
			viewGroup.getWindowVisibleDisplayFrame(r)

			val heightDiff = viewGroup.rootView.height - (r.bottom - r.top)
			if (heightDiff > 100.pxToDp(context)) {
				shown()
			} else {
				hidden()
			}

		}
	}
}

/**
 * Open app in play store
 */
fun Context.openAppInPlayStore() {
	val uri = Uri.parse("market://details?id=$packageName")
	val goToMarket = Intent(Intent.ACTION_VIEW, uri)
	// To count with Play market backstack, After pressing back button,
	// to taken back to our application, we need to add following flags to intent.
	goToMarket.addFlags(
		Intent.FLAG_ACTIVITY_NO_HISTORY or
				Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
				Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
	try {
		startActivity(goToMarket)
	} catch (e: ActivityNotFoundException) {
		startActivity(
			Intent(
				Intent.ACTION_VIEW,
				Uri.parse("http://play.google.com/store/apps/details?id=$packageName")))
	}
}

/**
 * Get default status bar height
 */
fun Context.getStatusBarHeight(): Int {
	var result = 0
	val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
	if (resourceId > 0) {
		result = resources.getDimensionPixelSize(resourceId)
	}
	return result
}

/**
 * Get theme accent color
 */
//fun Context.getAccentColor(): Int {
//	val a = obtainStyledAttributes(TypedValue().data, intArrayOf(R.attr.colorAccent))
//	val color = a.getColor(0, 0)
//	a.recycle()
//	return color
//}

data class ButtonSpecs(
	val text: String? = null,
	val handler: (() -> Unit)? = null)

/**
 * Show default dialog
 */
//fun Activity.showDefaultDialog(
//	title: String,
//	message: String,
//	positiveButton: ButtonSpecs? = null,
//	negativeButton: ButtonSpecs? = null,
//	isCancelable: Boolean? = false,
//	dark: Boolean? = false) {
//
//	showAlertDialog(
//		window.decorView.rootView as ViewGroup,
//		alertType = AlertDialogType.DEFAULT,
//		title = title,
//		message = message,
//		positiveButton = positiveButton,
//		negativeButton = negativeButton,
//		isCancelable = isCancelable, dark = dark)
//}

/**
 * Show info dialog
 */
//fun Activity.showInfoDialog(
//	title: String,
//	message: String,
//	positiveButton: ButtonSpecs? = null,
//	negativeButton: ButtonSpecs? = null,
//	isCancelable: Boolean? = false,
//	dark: Boolean? = false) {
//
//	showAlertDialog(
//		window.decorView.rootView as ViewGroup,
//		alertType = AlertDialogType.INFO,
//		title = title,
//		message = message,
//		positiveButton = positiveButton,
//		negativeButton = negativeButton,
//		isCancelable = isCancelable, dark = dark)
//}

/**
 * Show confirmation dialog
 */
//fun Activity.showConfirmationDialog(
//	title: String,
//	message: String,
//	positiveButton: ButtonSpecs? = null,
//	negativeButton: ButtonSpecs? = null,
//	isCancelable: Boolean? = false,
//	dark: Boolean? = false) {
//
//	showAlertDialog(
//		window.decorView.rootView as ViewGroup,
//		alertType = AlertDialogType.CONFIRMATION,
//		title = title,
//		message = message,
//		positiveButton = positiveButton,
//		negativeButton = negativeButton,
//		isCancelable = isCancelable, dark = dark)
//}

/**
 * Show warning dialog
 */
//fun Activity.showWarningDialog(
//	title: String,
//	message: String,
//	positiveButton: ButtonSpecs? = null,
//	negativeButton: ButtonSpecs? = null,
//	isCancelable: Boolean? = false,
//	dark: Boolean? = false) {
//
//	showAlertDialog(
//		window.decorView.rootView as ViewGroup,
//		alertType = AlertDialogType.WARNING,
//		title = title,
//		message = message,
//		positiveButton = positiveButton,
//		negativeButton = negativeButton,
//		isCancelable = isCancelable, dark = dark)
//}

/**
 * Show warning dialog
 */
//fun Activity.showErrorDialog(
//	title: String,
//	message: String,
//	positiveButton: ButtonSpecs? = null,
//	negativeButton: ButtonSpecs? = null,
//	isCancelable: Boolean? = false,
//	dark: Boolean? = false) {
//
//	showAlertDialog(
//		window.decorView.rootView as ViewGroup,
//		alertType = AlertDialogType.ERROR,
//		title = title,
//		message = message,
//		positiveButton = positiveButton,
//		negativeButton = negativeButton,
//		isCancelable = isCancelable, dark = dark)
//}

/**
 * Show success dialog
 */
//fun Activity.showSuccessDialog(
//	title: String,
//	message: String,
//	positiveButton: ButtonSpecs? = null,
//	negativeButton: ButtonSpecs? = null,
//	isCancelable: Boolean? = false,
//	dark: Boolean? = false) {
//
//	showAlertDialog(
//		window.decorView.rootView as ViewGroup,
//		alertType = AlertDialogType.SUCCESS,
//		title = title,
//		message = message,
//		positiveButton = positiveButton,
//		negativeButton = negativeButton,
//		isCancelable = isCancelable, dark = dark)
//}

/**
 * Show date picker dialog
 */
fun Activity.showDatePickerDialog(onDateSet: (Date) -> Unit) {
	val calendar = Calendar.getInstance()
	val datePickerDialog = DatePickerDialog(this,
		DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
			onDateSet.invoke(Date(year, month, dayOfMonth))
		}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
	datePickerDialog.datePicker.minDate = calendar.timeInMillis
	datePickerDialog.show()
}

/**
 * Show time picker dialog
 */
fun Activity.showTimePickerDialog(onTimeSet: (Time) -> Unit) {
	val calendar = Calendar.getInstance()
	val timePickerDialog = TimePickerDialog(this,
		TimePickerDialog.OnTimeSetListener { _, hour, minute ->
			onTimeSet(Time(hour, minute, 0))
		}, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
	timePickerDialog.show()
}

/**
 * Show short duration snackbar
 */
//fun Activity.showShortSnackbar(
//	message: String,
//	actionText: String? = null,
//	actionHandler: (() -> Unit)? = null,
//	type: SnackbarType? = null,
//	@ColorRes textColor: Int? = null,
//	@ColorRes backgroundColor: Int? = null) {
//
//	showSnackbar(
//		window.decorView.rootView as ViewGroup,
//		length = Snackbar.LENGTH_SHORT,
//		type = type,
//		message = message,
//		actionText = actionText,
//		actionHandler = actionHandler,
//		textColor = textColor,
//		backgroundColor = backgroundColor)
//}

/**
 * Show long duration snackbar
 */
//fun Activity.showLongSnackbar(
//	message: String,
//	actionText: String? = null,
//	actionHandler: (() -> Unit)? = null,
//	type: SnackbarType? = null,
//	@ColorRes textColor: Int? = null,
//	@ColorRes backgroundColor: Int? = null) {
//
//	showSnackbar(
//		window.decorView.rootView as ViewGroup,
//		length = Snackbar.LENGTH_LONG,
//		type = type,
//		message = message,
//		actionText = actionText,
//		actionHandler = actionHandler,
//		textColor = textColor,
//		backgroundColor = backgroundColor)
//}

/**
 * Show indefinite duration snackbar
 */
//fun Activity.showIndefiniteSnackbar(
//	message: String,
//	actionText: String? = null,
//	actionHandler: (() -> Unit)? = null,
//	type: SnackbarType? = null,
//	@ColorRes textColor: Int? = null,
//	@ColorRes backgroundColor: Int? = null) {
//
//	showSnackbar(
//		window.decorView.rootView as ViewGroup,
//		length = Snackbar.LENGTH_INDEFINITE,
//		type = type,
//		message = message,
//		actionText = actionText,
//		actionHandler = actionHandler,
//		textColor = textColor,
//		backgroundColor = backgroundColor)
//}

/**
 * Open Gallery and select media
 */
//fun FragmentActivity.choosePhotoFromGallery(resultCode: Int?) {
//	val galleryIntent = Intent(
//		Intent.ACTION_PICK,
//		MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//	startActivityForResult(galleryIntent, resultCode ?: ResultCode.GALLERY_PICK)
//}

/**
 * Open camera and take photo
 */
//fun FragmentActivity.takePhotoFromCamera(resultCode: Int?) {
//	val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//	startActivityForResult(cameraIntent, resultCode ?: ResultCode.TAKE_PHOTO)
//}

/**
 * Save image file
 */
//fun FragmentActivity.saveImage(
//	bitmap: Bitmap,
//	directory: String,
//	onSaveFinished: (Boolean, String?) -> Unit) {
//
//	val bytes = ByteArrayOutputStream()
//	bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
//	val wallpaperDirectory = File("${Environment.getExternalStorageDirectory()}$directory")
//	// have the object build the directory structure, if needed.
//	if (!wallpaperDirectory.exists()) {
//		wallpaperDirectory.mkdirs()
//	}
//	try {
//		val f = File(
//			wallpaperDirectory, Calendar.getInstance()
//				.timeInMillis.toString() + ".jpg")
//		f.createNewFile()
//		val fo = FileOutputStream(f)
//		fo.write(bytes.toByteArray())
//		MediaScannerConnection.scanFile(
//			this,
//			arrayOf(f.path),
//			arrayOf("image/jpeg"), null)
//		fo.close()
//		e("File Saved::--->" + f.absolutePath)
//		onSaveFinished(true, f.absolutePath)
//	} catch (e: IOException) {
//		e.printStackTrace()
//		onSaveFinished(false, null)
//	}
//}

/**
 * Camera permission handler
 */
//fun Activity.handleCameraPermission(onAccepted: (() -> Unit)? = null) {
//	handlePermission(
//		textPermission = "Camera",
//		permission = Manifest.permission.CAMERA,
//		onAccepted = onAccepted)
//}
//
///**
// * Contacts permission handler
// */
//fun Activity.handleReadContactsPermission(onAccepted: (() -> Unit)? = null) {
//	handlePermission(
//		textPermission = "Read Contacts",
//		permission = Manifest.permission.READ_CONTACTS,
//		onAccepted = onAccepted)
//}
//
///**
// * NFC permission handler
// */
//fun Activity.handleNFCPermission(onAccepted: (() -> Unit)? = null) {
//	handlePermission(
//		textPermission = "NFC",
//		permission = Manifest.permission.NFC,
//		onAccepted = onAccepted)
//}
//
///**
// * Audio permission handler
// */
//fun Activity.handleRecordAudioPermissions(onAccepted: (() -> Unit)? = null) {
//	handlePermission(
//		textPermission = "Record Audio",
//		permission = Manifest.permission.RECORD_AUDIO,
//		onAccepted = onAccepted)
//}
//
///**
// * Write Storage permission handler
// */
//fun Activity.handleWriteStoragePermission(onAccepted: (() -> Unit)? = null) {
//	handlePermission(
//		textPermission = "Write Storage",
//		permission = Manifest.permission.WRITE_EXTERNAL_STORAGE,
//		onAccepted = onAccepted)
//}
//
///**
// * Location permission handler
// */
//fun Activity.handleFineLocationPermission(onAccepted: (() -> Unit)? = null) {
//	handlePermission(
//		textPermission = "Location",
//		permission = Manifest.permission.ACCESS_FINE_LOCATION,
//		onAccepted = onAccepted)
//}
//
///**
// * SMS permission handler
// */
//fun Activity.handleSendSMSPermission(onAccepted: (() -> Unit)? = null) {
//	handlePermission(
//		textPermission = "Send SMS",
//		permission = Manifest.permission.SEND_SMS,
//		onAccepted = onAccepted)
//}
//
///**
// * Read phone state permission handler
// */
//fun Activity.handleReadPhoneStatePermission(onAccepted: (() -> Unit)? = null) {
//	handlePermission(
//		textPermission = "Read Phone State",
//		permission = Manifest.permission.READ_PHONE_STATE,
//		onAccepted = onAccepted)
//}

//fun Activity.handlePermission(
//	textPermission: String,
//	permission: String,
//	onAccepted: (() -> Unit)?) {
//	// permission handler
//	val permissionRequest = permissionsBuilder(permission).build()
//	permissionRequest.onAccepted {
//		onAccepted?.invoke()
//	}.onDenied {
//		permissionRequest.send()
//	}.onPermanentlyDenied {
//		// prompt user to update permissions in settings
//		showIndefiniteSnackbar(
//			message = "$textPermission access required. Go to Permissions -> Switch $textPermission ON",
//			actionText = "GO TO\nSETTINGS",
//			actionHandler = {
//				val intent =
//					Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
//				startActivity(intent.newTask())
//			})
//	}.onShouldShowRationale { _, permissionNonce ->
//		// request for permission
//		showIndefiniteSnackbar(
//			message = "$textPermission access required",
//			actionText = "REQUEST\nPERMISSION",
//			actionHandler = { permissionNonce.use() })
//	}.send()    // check
//}