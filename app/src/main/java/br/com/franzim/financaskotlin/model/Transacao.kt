package br.com.franzim.financaskotlin.model

import br.com.franzim.financaskotlin.model.enums.Tipo
import java.math.BigDecimal
import java.util.Calendar

class Transacao(val valor: BigDecimal,
                val categoria: String,
                val tipo: Tipo,
                val data: Calendar = Calendar.getInstance()) {

    constructor(valor: BigDecimal, tipo: Tipo) : this(valor, "Indefinida", tipo)
    constructor(valor: BigDecimal, tipo: Tipo, data: Calendar) : this(valor, "Indefinida", tipo, data)
}