package br.com.franzim.financaskotlin.ui.dialog

import android.content.Context
import android.view.View
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.model.enums.Tipo

class AdicionaTransacaoDialog(
        private val context: Context,
        private val decorView: View?) : FormularioTransacaoDialog(context, decorView) {

    override val TAG: String get() = "AdicionaTransacaoDialog"

    override fun tituloPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) R.string.adiciona_receita else R.string.adiciona_despesa
    }


}