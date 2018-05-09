package br.com.franzim.financaskotlin.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.extension.formatDateBR
import br.com.franzim.financaskotlin.extension.formatMoedaBR
import br.com.franzim.financaskotlin.extension.limitaCaracteresApos
import br.com.franzim.financaskotlin.model.Transacao
import br.com.franzim.financaskotlin.model.enums.Tipo
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val transacoes: List<Transacao>, private val context: Context): BaseAdapter() {

    private val limiteCaracteresCategoria = 14

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[posicao]

        if (transacao.tipo == Tipo.RECEITA) {
            view.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.receita))
            view.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        } else {
            view.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.despesa))
            view.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }

        view.transacao_valor.text = transacao.valor.formatMoedaBR()
        view.transacao_categoria.text = transacao.categoria.limitaCaracteresApos(limiteCaracteresCategoria)
        view.transacao_data.text = transacao.data.formatDateBR()

//      If muito loko que retona um valor
//        val result: String = if (true) {
//            "if"
//        } else {
//            "else"
//        }

        return view
    }

    override fun getItem(posicao: Int): Transacao     {
        return transacoes[posicao]
    }

    override fun getItemId(p0: Int): Long {
        return 0;
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}