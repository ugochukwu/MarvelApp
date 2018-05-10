package com.onwordiesquire.mobile.marvelapp.util

import android.widget.EditText

/**
 * Created by michel.onwordi on 09/08/2017.
 */

val EditText.textValue: String
    get() = this.text.toString()