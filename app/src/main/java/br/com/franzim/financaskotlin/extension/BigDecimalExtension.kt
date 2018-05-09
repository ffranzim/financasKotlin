package br.com.franzim.financaskotlin.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatMoedaBR() : String {
    val formatRS = DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatRS.format(this).replace("R$", "R$ ")
}