package com.seu.pacote.aqui.com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class HistoricoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        val textHistorico = findViewById<TextView>(R.id.textHistorico)

        // Pegando dados da intent
        val historico = intent.getStringExtra("historico")
        textHistorico.text = historico ?: "Nenhum cálculo registrado."
    }
}
