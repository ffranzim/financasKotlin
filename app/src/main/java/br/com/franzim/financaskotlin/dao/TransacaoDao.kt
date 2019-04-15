package br.com.franzim.financaskotlin.dao

import br.com.franzim.financaskotlin.model.Transacao

class TransacaoDao {


    companion object {
       private val transacoes: MutableList<Transacao> = mutableListOf()
    }

    val transacoes: List<Transacao> = Companion.transacoes

    fun add(transacao: Transacao) {
        Companion.transacoes.add(transacao)
    }

    fun update(transacao: Transacao, posicao: Int) {
        Companion.transacoes[posicao] = transacao
    }

    fun remove(posicao: Int) {
        Companion.transacoes.removeAt(posicao)
    }


}