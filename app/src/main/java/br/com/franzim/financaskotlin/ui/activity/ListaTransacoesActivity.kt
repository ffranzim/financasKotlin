package br.com.franzim.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.ui.adapter.ListaTransacoesAdapter
import br.com.franzim.financaskotlin.model.Transacao
import br.com.franzim.financaskotlin.model.enums.Tipo
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes : List<Transacao> = transacoesExemplo()
        setAdapter(transacoes)
    }

    private fun setAdapter(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesExemplo(): List<Transacao> {
        return listOf(
                Transacao(BigDecimal(20.50), "Alimentação", Tipo.DESPESA, Calendar.getInstance()),
                Transacao(BigDecimal(20.50), "almoço de final de semana", Tipo.DESPESA, Calendar.getInstance()),
                Transacao(categoria = "Alimentação", tipo = Tipo.DESPESA, data = Calendar.getInstance(), valor = BigDecimal(20.50)),
                Transacao(categoria = "Alimentação", tipo = Tipo.DESPESA, valor = BigDecimal(20.50)),
                Transacao(BigDecimal(2000), Tipo.RECEITA, Calendar.getInstance()),
                Transacao(BigDecimal(80.00), Tipo.RECEITA),
                Transacao(BigDecimal(80.00), Tipo.RECEITA),
                Transacao(BigDecimal(80.00), Tipo.DESPESA))
    }
}