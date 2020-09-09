package com.ubaidmerchant.findamatch.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaidmerchant.findamatch.data.local.dao.ResultsDao
import com.ubaidmerchant.findamatch.model.ResultsModel

/**
 * Abstract FAM database.
 * It provides DAO [ResultsDao] by using method [getResultsDao].
 */
@Database(
    entities = [ResultsModel::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class FamResultsDatabase : RoomDatabase() {

    /**
     * @return [ResultsDao] Fam Results Data Access Object.
     */
    abstract fun getResultsDao(): ResultsDao

    companion object {
        const val DB_NAME = "fam_database"

        @Volatile
        private var INSTANCE: FamResultsDatabase? = null

        fun getInstance(context: Context): FamResultsDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FamResultsDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}