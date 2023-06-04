package com.example.drinks.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinks.utils.Drinks_TABLE
import javax.inject.Inject

@Entity(tableName = Drinks_TABLE)
data class Drink @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int ? = null ,
    val name: String,
    val description: String,
    val image: Int,
    val combinations: List<Combination>
)
