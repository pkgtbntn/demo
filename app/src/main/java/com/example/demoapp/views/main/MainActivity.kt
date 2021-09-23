package com.example.demoapp.views.main

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.demoapp.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val loader by lazy {
        AlertDialog.Builder(this).create().apply {
            @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            setView(layoutInflater.inflate(R.layout.dialog_loader, activity_main))
        }
    }

    /** View Methods **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // state observer
        viewModel.state.observe(this, Observer { state -> setState(state) })
    }

    override fun onPause() {
        super.onPause()
        loader.dismiss()
    }

    /** Private Methods **/
    private fun setState(newState: MainState) {
        with(newState) { loader.apply { if (isLoading) show() else dismiss() } }
    }

}