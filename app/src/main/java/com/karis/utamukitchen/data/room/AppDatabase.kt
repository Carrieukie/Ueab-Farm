package com.karis.utamukitchen.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karis.utamukitchen.models.Food

@Database(entities = [Food::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {


    abstract fun foodDao(): FoodDao
}