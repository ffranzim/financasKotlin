package br.com.franzim.financaskotlin.ui.dialog

import android.content.Context
import android.view.View
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.extension.formatDateBR
import br.com.franzim.financaskotlin.model.Transacao
import br.com.franzim.financaskotlin.model.enums.Tipo

class AlteraTransacaoDialog(
        private val context: Context,
        decorView: View?) : FormularioTransacaoDialog(context, decorView) {

    override val TAG: String get() = "AlteraTransacaoDialog"

    fun show(transacao: Transacao, delegate: (transacao: Transacao) -> Unit) {

        val tipoTransacao = transacao.tipo

        super.show(tipoTransacao, delegate)

        this.campoValor.setText(transacao.valor.toString())
        this.campoData.setText(transacao.data.formatDateBR())


        val categorias = confCampoCategoria(tipoTransacao)
        this.campoCategoria.adapter = this.confCampoCategoria(tipoTransacao)
        val posicaoCategoria = categorias.getPosition(transacao.categoria)
        this.campoCategoria.setSelection(posicaoCategoria, true)

    }

    override fun tituloPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) R.string.altera_receita else R.string.altera_despesa
    }
}