package br.com.franzim.financaskotlin.extension


fun String.limitaCaracteresApos(tamanhoMaximo: Int) : String {

    if(this.length > tamanhoMaximo) {
        val primeiroCaractere = 0
        return "${this.substring(primeiroCaractere, tamanhoMaximo)}..."
    }

    return this
}