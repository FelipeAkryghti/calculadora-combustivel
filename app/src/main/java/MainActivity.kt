package com.seu.pacote.aqui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        }
    }
}
