package com.marcecuevas.easybuy.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toSimpleString(date: Date) : String {
        val format = SimpleDateFormat("d' de 'MMMM' de 'yyyy",Locale("es"))
        return format.format(date)
    }
}