package com.karis.utamukitchen.mainRepository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karis.utamukitchen.data.room.AppDatabase
import com.karis.utamukitchen.models.Food
import javax.inject.Inject

class MainRepository @Inject constructor(private val appDatabase: AppDatabase) {

    val foodList =  appDatabase.foodDao().getAllFoods();
    val totalPrice = appDatabase.foodDao().getTotalPrice()

    suspend fun addFood(food: Food){
        appDatabase.foodDao().addFood(food)
    }



}