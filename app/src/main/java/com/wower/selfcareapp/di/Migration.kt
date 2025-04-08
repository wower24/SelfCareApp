package com.wower.selfcareapp.di

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
        "CREATE TABLE IF NOT EXISTS `journal_prompts` (" +
                "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "`prompt` TEXT NOT NULL, " +
                "`isNotUsed` INTEGER NOT NULL)"
        )
    }
}