package com.seu.pacote.aqui.com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.SobreNosActivity
import com.example.myapplication.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputGasolina = findViewById<EditText>(R.id.inputGasolina)
        val inputAlcool = findViewById<EditText>(R.id.inputAlcool)
        val botaoCalcular = findViewById<Button>(R.id.botaoCalcular)
        val textResultado = findViewById<TextView>(R.id.textResultado)

        botaoCalcular.setOnClickListener {
            val precoGasolina = inputGasolina.text.toString()
            val precoAlcool = inputAlcool.text.toString()

            if (precoGasolina.isEmpty() || precoAlcool.isEmpty()) {
                Toast.makeText(this, "Preencha os dois campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gasolina = precoGasolina.toFloat()
            val alcool = precoAlcool.toFloat()

            val resultado = alcool / gasolina

            if (resultado < 0.7) {
                textResultado.text = "Compensa usar Álcool!"
            } else {
                textResultado.text = "Compensa usar Gasolina!"
            }
            val botaoHistorico = findViewById<Button>(R.id.botaoHistorico)
            val historicoRealizado = mutableListOf<String>() // Aqui guardamos os cálculos

            botaoHistorico.setOnClickListener {
                val intent = Intent(this, HistoricoActivity::class.java)
                val historicoTexto = historicoRealizado.joinToString("\n")
                intent.putExtra("historico", historicoTexto)
                startActivity(intent)
                val tipoCombustivel = if (resultado < 0.7) "Álcool" else "Gasolina"
                historicoRealizado.add("Álcool: R$ $alcool | Gasolina: R$ $gasolina → Melhor: $tipoCombustivel")

            }
        }
        val botaoSobreNos = findViewById<Button>(R.id.botaoSobreNos)
        botaoSobreNos.setOnClickListener {
            val intent = Intent(this, SobreNosActivity::class.java)
            startActivity(intent)
        }

    }
}