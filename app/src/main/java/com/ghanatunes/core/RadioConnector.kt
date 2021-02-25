package com.ghanatunes.core

import android.util.Log

internal class RadioConnector (radioSource : String) {
    private var RadioSource: String? = null
    init {
        this.RadioSource = radioSource
    }

    fun parseRadioSource(): String? {
        return RadioSource
    }
}