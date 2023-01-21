package com.flow.assignment.module

import android.content.Context
import androidx.room.Room
import com.flow.assignment.database.HistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Provides
    fun provideUserDao(historyDatabase: HistoryDatabase) = historyDatabase.historyDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            HistoryDatabase::class.java,
            "user_db"
        ).fallbackToDestructiveMigration()
            .build()

}