package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.model.Calculo
import com.example.myapplication.R
import kotlinx.coroutines.launch

class HistoricoActivity : AppCompatActivity() {

    private lateinit var textHistorico: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        textHistorico = findViewById(R.id.textHistorico)

        val db = AppDatabase.getDatabase(this)
        val dao = db.calculoDao()

        lifecycleScope.launch {
            val listaCalculos: List<Calculo> = dao.buscarTodos()
            if (listaCalculos.isEmpty()) {
                textHistorico.text = "Nenhum cálculo registrado."
            } else {
                val historicoFormatado = listaCalculos.joinToString("\n") {
                    "Álcool: R$ ${it.precoAlcool} | Gasolina: R$ ${it.precoGasolina} → Melhor: ${it.resultado}"
                }
                textHistorico.text = historicoFormatado
                // Log para verificar a lista carregada
                println("Histórico carregado: ${listaCalculos.size} cálculos")
            }
        }
    }
}
