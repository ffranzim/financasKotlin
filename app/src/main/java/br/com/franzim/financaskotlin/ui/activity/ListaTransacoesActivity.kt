package br.com.franzim.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.delegate.TransacaoDelegate
import br.com.franzim.financaskotlin.model.Transacao
import br.com.franzim.financaskotlin.model.enums.Tipo
import br.com.franzim.financaskotlin.ui.ResumoView
import br.com.franzim.financaskotlin.ui.adapter.ListaTransacoesAdapter
import br.com.franzim.financaskotlin.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private var transacoes: MutableList<Transacao> = mutableListOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val decorView = window.decorView
        atualizaTotais(decorView as ViewGroup)

        confFab(decorView)
    }

    private fun confFab(decorView: ViewGroup) {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialog(decorView, Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialog(decorView, Tipo.DESPESA)
        }
    }

    private fun chamaDialog(decorView: ViewGroup, tipo: Tipo) {
        AdicionaTransacaoDialog(this, decorView as ViewGroup).show(tipo, object : TransacaoDelegate {
            override fun delegate(transacao: Transacao) {
                transacoes.add(transacao)
                lista_transacoes_adiciona_menu.close(true)
                atualizaTotais(decorView)
            }

        })
    }

    private fun setAdapter() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun atualizaTotais(decorView: ViewGroup) {
        setAdapter()
        ResumoView(this, decorView).calculaTotais(transacoes)
    }

}