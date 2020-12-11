package com.karis.utamukitchen.mainRepository


import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karis.utamukitchen.data.room.AppDatabase
import com.karis.utamukitchen.models.Food
import javax.inject.Inject

class MainRepository @Inject constructor(private val appDatabase: AppDatabase) {

    suspend fun addFood(food: Food){
        appDatabase.foodDao().addFood(food)
    }

    fun getAllFoods(): LiveData<List<Food>>{
        return appDatabase.foodDao().getAllFoods()
    }

}