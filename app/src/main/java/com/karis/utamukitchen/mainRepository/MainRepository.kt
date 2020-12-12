package com.karis.utamukitchen.mainRepository


import com.karis.utamukitchen.data.room.AppDatabase
import com.karis.utamukitchen.models.Food
import javax.inject.Inject

class MainRepository @Inject constructor(private val appDatabase: AppDatabase) {

    suspend fun addFood(food: Food){
        appDatabase.foodDao().addFood(food)
    }

    suspend fun getAllFoods(): List<Food>{
        return appDatabase.foodDao().getAllFoods()
    }

}