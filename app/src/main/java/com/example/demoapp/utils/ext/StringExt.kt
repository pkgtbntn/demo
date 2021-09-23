package com.example.demoapp.utils.ext

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import android.util.Patterns
import java.net.URLEncoder
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Sanitize
 */
fun String.sanitize(): String =
	this.replace(" {2}".toRegex(), " ")
		.replace("\n\n\n+".toRegex(), "\n\n")
		.trim()

/**
 * Phone number sanitation
 */
fun String.sanitizePhoneNumber(): String =
	this.sanitize()
		.replace("-", "")
		.replace("(", "")
		.replace(")", "")
		.replace(" ", "")
		.trim()


/**
 * Prefix a url with "http" if it isn't yet prefixed
 */
fun String.precedeLinkWithHttp() = if (!this.startsWith("http")) "http://$this" else this

/**
 * Prefix a url with "https" if it isn't yet prefixed
 */
fun String.precedeLinkWithHttps() = if (!this.startsWith("http")) "https://$this" else this

/**
 * Checks if starts with a vowel
 */
fun String.startsWithVowel(): Boolean = "AEIOUaeiou".indexOf(this.first()) != -1

/**
 * Check if valid name
 */
fun String.isValidName(): Boolean =
	Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z])[a-zA-Z',.-]*)*\$").matcher(this).matches()

/**
 * Check if not valid name
 */
fun String.isNotValidName(): Boolean = !isValidName()

/**
 * Check if valid email
 */
fun String.isValidEmail(): Boolean = matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$".toRegex())

/**
 * Check if not valid email
 */
fun String.isNotValidEmail(): Boolean = !isValidEmail()

/**
 * Check if valid phone number
 */
fun String.isValidPhoneNumber(): Boolean = android.util.Patterns.PHONE.matcher(this).matches()

/**
 * Check if not valid phone number
 */
fun String.isNotValidPhoneNumber(): Boolean = !isValidPhoneNumber()

/**
 * Check if valid PH phone number
 */
fun String.isValidPHPhoneNumber(): Boolean =
	Pattern.compile("^(09|\\+639)\\d{9}$", Pattern.CASE_INSENSITIVE).matcher(this).find()

/**
 * Check if not valid PH phone number
 */
fun String.isNotValidPHPhoneNumber() = !isValidPHPhoneNumber()

/**
 * Check if valid IP address
 */
fun String.isValidIpAddress(): Boolean{
	return if (this.sanitize().isNotEmpty()) Patterns.IP_ADDRESS.matcher(this.sanitize()).matches() else false
}

/**
 * Check if contains letters
 */
fun String.hasLetters(): Boolean = contains("[A-Za-z0-9]+".toRegex())

/**
 * Check if doesn't contain letters
 */
fun String.hasNoLetters(): Boolean = !hasLetters()

/**
 * Check if contains digits
 */
fun String.hasDigits(): Boolean = matches(".*\\d+.*".toRegex())

/**
 * Check if doesn't contain digits
 */
fun String.hasNoDigits(): Boolean = !hasDigits()

/**
 * Remove digit characters in string
 */
fun String.removeDigits(): String = replace("\\d".toRegex(), "")

/**
 * Add commas to currency figure
 */
fun String.addCurrencyComma(locale: Locale): String {
	return try {
		val numberFormat = NumberFormat.getNumberInstance(locale)
		numberFormat.format(this.toDouble())
	} catch (e: Exception) {
		this
	}
}

/**
 * URL Encode for URL queries etc.
 */
fun String.urlEncode(): String = URLEncoder.encode(this, "utf-8")

/**
 * Convert to Editable for Edit text utility
 */
fun String.toEditable() = SpannableStringBuilder(this)

/**
 * Convert date string [fromFormat] to Date Object
 */
@SuppressLint("SimpleDateFormat")
fun String.dateStringToDate(fromFormat: String = "yyyy-MM-dd"): Date {
	val dateFormat = SimpleDateFormat(fromFormat)
	return dateFormat.parse(this)
}

/**
 * Convert date string [fromFormat] to [toFormat] date string
 */
@SuppressLint("SimpleDateFormat")
fun String.dateStringToFormattedDate(fromFormat: String = "yyyy-MM-dd", toFormat: String = "MMMM dd, yyyy"): String {
	return try {
		val dateFormat = SimpleDateFormat(fromFormat)
		val date = dateFormat.parse(this)

		SimpleDateFormat(toFormat).format(date)
	} catch (e: Exception) { this }
}

/**
 * Convert date string to millis
 */
@SuppressLint("SimpleDateFormat")
fun String.dateToMillis(format: String = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"): Long {
	return try {
		val dateFormat = SimpleDateFormat(format)
		dateFormat.parse(this).time
	} catch (e: Exception) { 0.toLong() }
}