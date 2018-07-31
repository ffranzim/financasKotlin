package br.com.franzim.financaskotlin.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.extension.convertParaCalendar
import br.com.franzim.financaskotlin.extension.formatDateBR
import br.com.franzim.financaskotlin.model.Transacao
import br.com.franzim.financaskotlin.model.enums.Tipo
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.Calendar.*

abstract class FormularioTransacaoDialog(
        private val context: Context,
        private val decorView: View?) {

    private val viewCriada = criaLayout()
    protected val campoCategoria = this.viewCriada.form_transacao_categoria
    protected val campoValor = viewCriada.form_transacao_valor
    protected val campoData = this.viewCriada.form_transacao_data

    abstract protected val TAG: String;

    fun show(tipoDialog: Tipo, delegate: (transacao: Transacao) -> Unit) {

        this.campoCategoria.adapter = confCampoCategoria(tipoDialog)
        configuraCampoData()

        confFormulario(tituloPor(tipoDialog), tipoDialog, delegate)
    }

    abstract protected fun tituloPor(tipo: Tipo) : Int

    private fun confFormulario(tituloDialog: Int, tipoDialog: Tipo, delegate: (transacao: Transacao) -> Unit) {
        AlertDialog.Builder(context)
                .setTitle(tituloDialog)
                .setView(this.viewCriada)
                .setPositiveButton(tituloDialog)
                        { _, _ ->

                            val valor = this.campoValor.text.toString()
                            val categoria = this.campoCategoria.selectedItem.toString()
                            val dt = this.campoData.text.toString()
                            val dtCalendar = convertParaCalendar(dt)

                            val transacaoReceita = Transacao(BigDecimal(valor), categoria, tipoDialog, dtCalendar)

                            delegate(transacaoReceita)
                        }
                .setNegativeButton("Cancelar", null)
                .show()
    }

    private fun configuraCampoData() {

        val hoje = getInstance()

        this.campoData.setText(getInstance().formatDateBR())
        this.campoData.setOnClickListener {
            DatePickerDialog(context,
                    { _, ano, mes, dia ->
                        val dtSelecionada = getInstance()
                        dtSelecionada.set(ano, mes, dia)
                        this.campoData.setText(dtSelecionada.formatDateBR())

                    },
                    hoje.get(YEAR),
                    hoje.get(MONTH),
                    hoje.get(DAY_OF_MONTH))
                    .show()
        }
    }

    protected fun confCampoCategoria(tipoDialog: Tipo): ArrayAdapter<CharSequence> {
        if (tipoDialog == Tipo.RECEITA) {
            return ArrayAdapter.createFromResource(context, R.array.categorias_de_receita, android.R.layout.simple_spinner_dropdown_item)
        }

        return ArrayAdapter.createFromResource(context, R.array.categorias_de_despesa, android.R.layout.simple_spinner_dropdown_item)
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context).inflate(R.layout.form_transacao, decorView as ViewGroup, false)
    }
}