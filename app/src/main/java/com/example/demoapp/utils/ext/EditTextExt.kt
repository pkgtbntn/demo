package com.example.demoapp.utils.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Check if blank
 */
fun EditText.isBlank(): Boolean = text.toString() == ""

/**
 * Add a text change listener
 * execute [afterTextChanged] when the [EditText]'s text changes
 */
fun EditText.onTextChangeListener(
	beforeTextChanged: ((String) -> Unit)? = null,
	onTextChanged: ((String) -> Unit)? = null,
	afterTextChanged: ((String) -> Unit)? = null) {
	this.addTextChangedListener(object : TextWatcher {
		override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			beforeTextChanged?.invoke(p0.toString())
		}

		override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			onTextChanged?.invoke(p0.toString())
		}

		override fun afterTextChanged(editable: Editable?) {
			afterTextChanged?.invoke(editable.toString())
		}
	})
}
