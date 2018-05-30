package br.com.franzim.financaskotlin.extension

import java.text.SimpleDateFormat
import java.util.*


fun String.limitaCaracteresApos(tamanhoMaximo: Int) : String {

    if(this.length > tamanhoMaximo) {
        val primeiroCaractere = 0
        return "${this.substring(primeiroCaractere, tamanhoMaximo)}..."
    }

    return this
}

fun convertParaCalendar(dt: String): Calendar {
    val formataBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dtParse = formataBrasileiro.parse(dt)
    val dtCalendar = Calendar.getInstance()
    dtCalendar.time = dtParse
    return dtCalendar
}