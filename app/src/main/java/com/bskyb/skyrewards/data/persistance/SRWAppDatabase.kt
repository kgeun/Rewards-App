package com.bskyb.skyrewards.data.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bskyb.skyrewards.data.model.SRWCustomerData
import com.bskyb.skyrewards.data.model.SRWRewardResult

@Database(entities = [SRWCustomerData::class, SRWRewardResult::class], version = 1, exportSchema = false)
abstract class SRWAppDatabase: RoomDatabase() {
  abstract fun SRWMainDao(): SRWMainDao

  companion object {
    const val DB_NAME = "skyrewards.db"

    @Volatile
    private var INSTANCE: SRWAppDatabase? = null

    fun getInstance(context: Context): SRWAppDatabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }

      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          SRWAppDatabase::class.java,
          DB_NAME
        ).allowMainThreadQueries()
          .build()

        INSTANCE = instance
        return instance
      }
    }
  }
}