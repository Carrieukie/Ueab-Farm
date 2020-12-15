package com.karis.utamukitchen.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.karis.utamukitchen.models.Food

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: Food)

    @Update
    suspend fun updateFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query("SELECT * FROM cart")
    fun getAllFoods(): LiveData<List<Food>>

    @Query("SELECT * FROM cart WHERE id = :id")
    fun getMovie(id: Int): LiveData<Food?>?

    @Query("SELECT SUM(price * numberOfItem) FROM cart")
    fun getTotalPrice(): LiveData<Int>

    @Query("SELECT EXISTS (SELECT 1 FROM cart)")
    fun ifEntryExists(): LiveData<Int>
}