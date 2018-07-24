package br.com.franzim.financaskotlin.ui.dialog

import android.content.Context
import android.view.View
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.delegate.TransacaoDelegate
import br.com.franzim.financaskotlin.extension.formatDateBR
import br.com.franzim.financaskotlin.model.Transacao
import br.com.franzim.financaskotlin.model.enums.Tipo

class AlteraTransacaoDialog(
        private val context: Context,
        decorView: View?) : FormularioTransacaoDialog(context, decorView) {

    override val TAG: String get() = "AlteraTransacaoDialog"

    fun show(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {

        val tipoTransacao = transacao.tipo

        super.show(tipoTransacao, transacaoDelegate)

//        configuraCampoData()
        this.campoValor.setText(transacao.valor.toString())
        this.campoData.setText(transacao.data.formatDateBR())


        val categorias = confCampoCategoria(tipoTransacao)


        this.campoCategoria.adapter = this.confCampoCategoria(tipoTransacao)
        val posicaoCategoria = categorias.getPosition(transacao.categoria)
//        val posicaoCategoria = categorias.indexOf(transacao.categoria)
        this.campoCategoria.setSelection(posicaoCategoria, true)

//        confFormulario(tituloDialog, tipoTransacao, transacaoDelegate)
    }

    override fun tituloPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) R.string.altera_receita else R.string.altera_despesa
    }

//    private val viewCriada = criaLayout()
//
//    private val campoCategoria = this.viewCriada.form_transacao_categoria
//    private val campoValor = viewCriada.form_transacao_valor
//    private val campoData = this.viewCriada.form_transacao_data
//
//    fun show(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {
//
//        configuraCampoData()
//
//        val tipoTransacao = transacao.tipo
//
//        this.campoValor.setText(transacao.valor.toString())
//        this.campoData.setText(transacao.data.formatDateBR())
//
//
//        val categorias = context.resources.getStringArray(categoriasPor(tipoTransacao))
//        this.campoCategoria.adapter = this.confCampoCategoria(tipoTransacao)
//        val posicaoCategoria = categorias.indexOf(transacao.categoria)
//        this.campoCategoria.setSelection(posicaoCategoria, true)
//
//        val tituloDialog = if (tipoTransacao == Tipo.RECEITA) R.string.altera_receita else R.string.altera_despesa
//        confFormulario(tituloDialog, tipoTransacao, transacaoDelegate)
//    }
//
//
//    private fun confFormulario(tituloDialog: Int, tipoDialog: Tipo, transacaoDelegate: TransacaoDelegate) {
//        AlertDialog.Builder(context)
//                .setTitle(tituloDialog)
//                .setView(this.viewCriada)
//                .setPositiveButton("Alterar")
//                        { _, _ ->
//                            val valor = this.campoValor.text.toString()
//                            val categoria = this.campoCategoria.selectedItem.toString()
//                            val dt = this.campoData.text.toString()
//                            val dtCalendar = convertParaCalendar(dt)
//
//                            val transacaoReceita = Transacao(BigDecimal(valor), categoria, tipoDialog, dtCalendar)
//
//                            transacaoDelegate.delegate(transacaoReceita)
//                        }
//                .setNegativeButton("Cancelar", null)
//                .show()
//    }
//
//
//    private fun configuraCampoData() {
//
//        val hoje = Calendar.getInstance()
//
//        this.campoData.setText(Calendar.getInstance().formatDateBR())
//        this.campoData.setOnClickListener {
//            DatePickerDialog(context,
//                    { _, ano, mes, dia ->
//                        val dtSelecionada = Calendar.getInstance()
//                        dtSelecionada.set(ano, mes, dia)
//                        this.campoData.setText(dtSelecionada.formatDateBR())
//
//                    },
//                    hoje.get(Calendar.YEAR),
//                    hoje.get(Calendar.MONTH),
//                    hoje.get(Calendar.DAY_OF_MONTH))
//                    .show()
//        }
//    }
//
//    private fun confCampoCategoria(tipoDialog: Tipo): ArrayAdapter<CharSequence>? {
//        return ArrayAdapter.createFromResource(context, categoriasPor(tipoDialog), android.R.layout.simple_spinner_dropdown_item)
//    }
//
//    private fun criaLayout(): View {
//        return LayoutInflater.from(context).inflate(R.layout.form_transacao, decorView as ViewGroup, false)
//    }
//
//    private fun categoriasPor(tipo: Tipo): Int {
//        if (tipo == Tipo.RECEITA) {
//            return R.array.categorias_de_receita
//        }
//        return R.array.categorias_de_despesa
//    }


}