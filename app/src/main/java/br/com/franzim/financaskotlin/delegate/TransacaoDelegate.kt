package br.com.franzim.financaskotlin.delegate

import br.com.franzim.financaskotlin.model.Transacao

interface TransacaoDelegate {

    fun delegate(transacao: Transacao)
}