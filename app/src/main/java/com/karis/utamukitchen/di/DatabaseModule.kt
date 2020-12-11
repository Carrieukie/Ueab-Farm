package com.karis.utamukitchen.di

import android.app.Application
import androidx.room.Room
import com.karis.utamukitchen.data.room.AppDatabase
import com.karis.utamukitchen.data.room.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun providesAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "food.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesHoursLeaderDao(appDatabase: AppDatabase): FoodDao {
        return appDatabase.foodDao()
    }


}