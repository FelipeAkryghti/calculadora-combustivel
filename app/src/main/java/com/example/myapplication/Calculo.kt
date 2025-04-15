package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculos")
data class Calculo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,  // Use Long em vez de Int
    val precoGasolina: Float,
    val precoAlcool: Float,
    val resultado: String
)

