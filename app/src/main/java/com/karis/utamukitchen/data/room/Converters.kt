package com.karis.utamukitchen.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.karis.utamukitchen.models.Food


import javax.inject.Inject


class Converters @Inject constructor(){

    var gson: Gson = Gson()

    @TypeConverter
    fun fromSnippet(food: Food): String {
        return gson.toJson(food)
    }

    @TypeConverter
    fun toSnippet(food: String): Food {
        return gson.fromJson(food, Food::class.java)
    }


}