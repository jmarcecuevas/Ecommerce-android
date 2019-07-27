package com.marcecuevas.hotelsapp.utils

import android.content.Context
import android.graphics.fonts.FontVariationAxis
import android.widget.TextView
import uk.co.chrisjenx.calligraphy.CalligraphyUtils

fun TextView.fontVariable(context: Context?, variable: FontVariable){
    context?.let {
        CalligraphyUtils.applyFontToTextView(it,this,Font.instance.pathForVariable(variable))
    }
}

fun TextView.bold(context: Context?){
    this.fontVariable(context,FontVariable.bold)
}

fun TextView.light(context: Context?){
    this.fontVariable(context,FontVariable.light)
}

inline fun <T: Any> guardLet(vararg elements: T?, closure: () -> Nothing): List<T> {
    return if (elements.all { it != null }) {
        elements.filterNotNull()
    } else {
        closure()
    }
}

inline fun <T: Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}