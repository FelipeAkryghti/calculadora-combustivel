package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.SobreNosActivity
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.model.Calculo
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputGasolina = findViewById<EditText>(R.id.inputGasolina)
        val inputAlcool = findViewById<EditText>(R.id.inputAlcool)
        val botaoCalcular = findViewById<Button>(R.id.botaoCalcular)
        val textResultado = findViewById<TextView>(R.id.textResultado)
        val botaoHistorico = findViewById<Button>(R.id.botaoHistorico)
        val botaoSobreNos = findViewById<Button>(R.id.botaoSobreNos)

        val db = AppDatabase.getDatabase(this)
        val dao = db.calculoDao()

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
            val melhor = if (resultado < 0.7) "Álcool" else "Gasolina"

            textResultado.text = "Compensa usar $melhor!"

            // Limpa os campos de input
            inputGasolina.setText("")
            inputAlcool.setText("")

            // Salva no banco de dados
            lifecycleScope.launch {
                dao.inserir(Calculo(precoGasolina = gasolina, precoAlcool = alcool, resultado = melhor))
                // Log para verificar inserção
                println("Cálculo inserido: Gasolina = $gasolina, Álcool = $alcool, Resultado = $melhor")
            }
        }


        botaoHistorico.setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }

        botaoSobreNos.setOnClickListener {
            val intent = Intent(this, SobreNosActivity::class.java)
            startActivity(intent)
        }
    }
}
