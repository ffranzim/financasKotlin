package br.com.franzim.financaskotlin.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.extension.formatMoedaBR
import br.com.franzim.financaskotlin.model.Transacao
import br.com.franzim.financaskotlin.model.enums.Tipo
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal


class ResumoView(private val context: Context, private val view: View) {

    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)
    private val corTotal = view.resumo_card_total

    fun calculaTotais(transacoes: List<Transacao>) {

        val totalReceita = totalizaValorPorTipo(transacoes, Tipo.RECEITA)
        val totalDespesa = totalizaValorPorTipo(transacoes, Tipo.DESPESA)
        val total = totalReceita.subtract(totalDespesa)

        preencheView(totalReceita, totalDespesa, total)
    }

    private fun totalizaValorPorTipo(transacoes: List<Transacao>, tipoTransasao: Tipo) =
            BigDecimal(transacoes.filter { it.tipo == tipoTransasao }
                    .sumByDouble { it.valor.toDouble() })

    private fun preencheView(totalReceita: BigDecimal, totalDespesa: BigDecimal, total: BigDecimal) {

        view?.let {
            with(view.resumo_card_receita) {
                text = totalReceita.formatMoedaBR()
                setTextColor(corReceita)
            }

            with(it.resumo_card_despesa) {
                text = totalDespesa.formatMoedaBR()
                setTextColor(corDespesa)
            }
        }

        corTotal.text = total.formatMoedaBR()

        if (total < BigDecimal.ZERO) {
            corTotal.setTextColor(corDespesa)
        } else {
            corTotal.setTextColor(corReceita)
        }
    }
}