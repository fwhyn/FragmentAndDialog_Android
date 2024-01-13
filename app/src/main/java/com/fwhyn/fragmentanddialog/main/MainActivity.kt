package com.fwhyn.fragmentanddialog.main

import android.os.Bundle
import androidx.activity.compose.setContent
import com.fwhyn.fragmentanddialog.common.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityContent()
        }
    }
}