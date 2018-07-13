package br.com.franzim.financaskotlin.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.com.franzim.financaskotlin.R
import br.com.franzim.financaskotlin.delegate.TransacaoDelegate
import br.com.franzim.financaskotlin.extension.convertParaCalendar
import br.com.franzim.financaskotlin.extension.formatDateBR
import br.com.franzim.financaskotlin.model.Transacao
import br.com.franzim.financaskotlin.model.enums.Tipo
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class AdicionaTransacaoDialog(private val context: Context, private val decorView: ViewGroup?) {

    fun show(tipoDialog: Tipo, transacaoDelegate: TransacaoDelegate) {

        val viewDialog = criaLayout()
        viewDialog.form_transacao_categoria.adapter = confCampoCategoria(tipoDialog)
        configuraCampoData(viewDialog)

        val tituloDialog = if (tipoDialog == Tipo.RECEITA) R.string.receita else R.string.despesa
        confFormulario(viewDialog, tituloDialog, tipoDialog, transacaoDelegate)
    }

    private fun confFormulario(viewDialog: View, tituloDialog: Int, tipoDialog: Tipo, transacaoDelegate: TransacaoDelegate) {
        AlertDialog.Builder(context)
                .setTitle(tituloDialog)
                .setView(viewDialog)
                .setPositiveButton(tituloDialog,
                        { _, _ ->

                            val valor = viewDialog.form_transacao_valor.text.toString()
                            val categoria = viewDialog.form_transacao_categoria.selectedItem.toString()
                            val dt = viewDialog.form_transacao_data.text.toString()
                            val dtCalendar = convertParaCalendar(dt)

                            val transacaoReceita = Transacao(BigDecimal(valor), categoria, tipoDialog, dtCalendar)

                            transacaoDelegate.delegate(transacaoReceita)
                        })
                .setNegativeButton("Cancelar", null)
                .show()
    }




    private fun configuraCampoData(viewDialog: View) {

        val hoje = Calendar.getInstance()

        viewDialog.form_transacao_data.setText(Calendar.getInstance().formatDateBR())
        viewDialog.form_transacao_data.setOnClickListener {
            DatePickerDialog(context,
                    { _, ano, mes, dia ->
                        val dtSelecionada = Calendar.getInstance()
                        dtSelecionada.set(ano, mes, dia)
                        viewDialog.form_transacao_data.setText(dtSelecionada.formatDateBR())

                    },
                    hoje.get(Calendar.YEAR),
                    hoje.get(Calendar.MONTH),
                    hoje.get(Calendar.DAY_OF_MONTH))
                    .show()
        }
    }

    private fun confCampoCategoria(tipoDialog: Tipo): ArrayAdapter<CharSequence>? {
        if (tipoDialog == Tipo.RECEITA) {
            return ArrayAdapter.createFromResource(context, R.array.categorias_de_receita, android.R.layout.simple_spinner_dropdown_item)
        }

        return ArrayAdapter.createFromResource(context, R.array.categorias_de_despesa, android.R.layout.simple_spinner_dropdown_item)
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context).inflate(R.layout.form_transacao, decorView as ViewGroup, false)
    }
}