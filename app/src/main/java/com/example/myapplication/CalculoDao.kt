package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.Calculo

@Dao
interface CalculoDao {
    @Insert
    suspend fun inserir(calculo: Calculo)

    @Query("SELECT * FROM calculos")
    suspend fun buscarTodos(): List<Calculo>
}
