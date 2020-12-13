package com.karis.utamukitchen.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karis.utamukitchen.models.Food

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: Food)

    @Query("SELECT * FROM cart")
    fun getAllFoods(): LiveData<List<Food>>

    @Query("SELECT SUM(price * numberOfItem) FROM cart")
    fun getTotalPrice(): LiveData<Int>


}