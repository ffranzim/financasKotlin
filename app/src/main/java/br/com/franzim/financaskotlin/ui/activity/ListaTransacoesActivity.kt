package br.com.franzim.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.model.Transacao
import br.com.franzim.financaskotlin.model.enums.Tipo
import br.com.franzim.financaskotlin.ui.ResumoView
import br.com.franzim.financaskotlin.ui.adapter.ListaTransacoesAdapter
import br.com.franzim.financaskotlin.ui.dialog.AdicionaTransacaoDialog
import br.com.franzim.financaskotlin.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private var transacoes: MutableList<Transacao> = mutableListOf();

    //    private lateinit var viewGroup: ViewGroup
        private val viewGroup by lazy {
            window.decorView
        }

//    private val viewGroup: View
//        get() {
//            return window.decorView
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        atualizaTotais()

        confFab()

    }

    private fun confFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialog(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialog(Tipo.DESPESA)
        }
    }

    private fun chamaDialog(tipo: Tipo) {
//        AdicionaTransacaoDialog(this, viewGroup).show(tipo,  { transacao ->
//                addTransacao(transacao)
//
//        })
//        AdicionaTransacaoDialog(this, viewGroup).show(tipo,  {
//                addTransacao(it)
//
//        })
        AdicionaTransacaoDialog(this, viewGroup).show(tipo) {
            addTransacao(it)
        }

    }


    private fun addTransacao(transacao: Transacao) {
        transacoes.add(transacao)
        lista_transacoes_adiciona_menu.close(true)
        atualizaTotais()
    }

    private fun setAdapter() {
        with(lista_transacoes_listview) {
            adapter = ListaTransacoesAdapter(transacoes, this@ListaTransacoesActivity)
            setOnItemClickListener { _, _, posicao, id ->
                val transacao = transacoes[posicao]
                showDialogAlteracao(transacao, posicao)

            }

        }
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
        lista_transacoes_listview.setOnItemClickListener { _, _, posicao, id ->
            val transacao = transacoes[posicao]
            showDialogAlteracao(transacao, posicao)

        }
    }

    private fun showDialogAlteracao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(this, viewGroup)
                .show(transacao) {
                    alteraTransacao(posicao, it)

                }
    }

    private fun alteraTransacao(posicao: Int, transacao: Transacao) {
        transacoes[posicao] = transacao
        lista_transacoes_adiciona_menu.close(true)
        atualizaTotais()
    }

    private fun atualizaTotais() {
        setAdapter()
        ResumoView(this, viewGroup).calculaTotais(transacoes)

    }
}