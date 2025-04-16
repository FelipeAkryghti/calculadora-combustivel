package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
                val historicoFormatado = listaCalculos.joinToString("\n\n") {
                    "Álcool: R$ ${it.precoAlcool} | Gasolina: R$ ${it.precoGasolina} Melhor: ${it.resultado}"
                }
                textHistorico.text = historicoFormatado
                // Log para verificar a lista carregada
                println("Histórico carregado: ${listaCalculos.size} cálculos")
            }
        }

        val botaoVoltar = findViewById<Button>(R.id.botaoVoltar)
        botaoVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val botaoLimpar = findViewById<Button>(R.id.botaoLimpar)
        botaoLimpar.setOnClickListener {
            lifecycleScope.launch {
                dao.apagarTodos() // você precisa ter esse método no DAO
                textHistorico.text = "Nenhum cálculo registrado."
                println("Histórico apagado com sucesso.")
            }
        }

    }
}
