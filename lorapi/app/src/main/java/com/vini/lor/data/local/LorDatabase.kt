package com.vini.lor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vini.lor.data.remote.AmericasLeaderboardsDataDto

@Database(entities = [AmericasLeaderboardsDataDto::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class LorDatabase : RoomDatabase() {
    abstract fun lorLeaderboardDao(): LorLeaderboardDao
}