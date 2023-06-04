package com.example.drinks.utils

import androidx.room.TypeConverter
import com.example.drinks.model.Combination
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromCombinationToGson(combination: List<Combination>): String = Gson().toJson(combination)

    @TypeConverter
    fun fromGsonToCombination(combinationString: String): List<Combination> {
        val listType = object : TypeToken<List<Combination>>() {}.type
        return Gson().fromJson(combinationString, listType)
    }

}