package com.karis.utamukitchen.di

//import com.karis.utamukitchen.data.room.AppDatabase
import com.karis.utamukitchen.data.room.AppDatabase
import com.karis.utamukitchen.mainRepository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule @Inject constructor() {

    @Provides
    fun provideMainrepository(appDatabase: AppDatabase): MainRepository {
        return MainRepository(appDatabase)
    }


}