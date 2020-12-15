package com.karis.utamukitchen.mainRepository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karis.utamukitchen.data.room.AppDatabase
import com.karis.utamukitchen.models.Food
import javax.inject.Inject

class MainRepository @Inject constructor(private val appDatabase: AppDatabase) {

    val foodList =  appDatabase.foodDao().getAllFoods()
    val totalPrice = appDatabase.foodDao().getTotalPrice()
    val ifExists = appDatabase.foodDao().ifEntryExists()

    fun getFood(id :Int): LiveData<Food?>? {
        return appDatabase.foodDao().getMovie(id);
    }

    suspend fun addFood(food: Food){
        appDatabase.foodDao().addFood(food)
    }

    suspend fun updateFood(food: Food){
        appDatabase.foodDao().updateFood(food)
    }

    suspend fun deleteFood(food: Food){
        appDatabase.foodDao().deleteFood(food)
    }

}