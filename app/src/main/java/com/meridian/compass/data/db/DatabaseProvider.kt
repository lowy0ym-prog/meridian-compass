package com.meridian.compass.data.db

import android.content.Context
import androidx.room.Room

/**
 * Database provider singleton.
 */
object DatabaseProvider {
    @Volatile
    private var INSTANCE: MeridianDatabase? = null

    fun getDatabase(context: Context): MeridianDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MeridianDatabase::class.java,
                "meridian_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
