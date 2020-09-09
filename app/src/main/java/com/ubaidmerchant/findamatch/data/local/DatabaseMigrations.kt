package com.ubaidmerchant.findamatch.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaidmerchant.findamatch.model.ResultsModel

object DatabaseMigrations {
    const val DB_VERSION = 1

    val MIGRATIONS: Array<Migration>
        get() = arrayOf(migration12())

    private fun migration12(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${ResultsModel.TABLE_NAME} ADD COLUMN body TEXT")
        }
    }
}