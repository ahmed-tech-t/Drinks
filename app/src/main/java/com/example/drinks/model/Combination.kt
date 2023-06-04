package com.example.drinks.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class Combination @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val value: Int
)
