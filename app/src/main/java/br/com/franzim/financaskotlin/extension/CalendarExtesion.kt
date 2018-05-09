package br.com.franzim.financaskotlin.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatDateBR() : String {
    val formatoBR = "dd/MM/yyyy"
    val sdf = SimpleDateFormat(formatoBR)
    return sdf.format(this.time)
}