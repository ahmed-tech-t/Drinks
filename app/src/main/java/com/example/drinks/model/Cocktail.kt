package com.example.drinks.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinks.utils.Cocktail_TABLE
import javax.inject.Inject

@Entity(tableName = Cocktail_TABLE)
data class Cocktail(
    @PrimaryKey(autoGenerate = true)
    val id: Int? =null,
    var image: String? = null,
    var name: String ? = null,
    val drinks: ArrayList<DrinkCocktail>
)