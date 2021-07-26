package com.bskyb.skyrewards.di

import android.content.Context
import androidx.room.Room
import com.bskyb.skyrewards.data.persistance.SRWAppDatabase
import com.bskyb.skyrewards.data.persistance.SRWMainDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideMainDao(appDatabase: SRWAppDatabase): SRWMainDao = appDatabase.SRWMainDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): SRWAppDatabase {
        return Room.databaseBuilder(
            appContext,
            SRWAppDatabase::class.java,
            "skyrewards.db"
        ).build()
    }

//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext appContext: Context): SRWAppDatabase {
//        return SRWAppDatabase.getInstance(appContext)
//    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
}