package com.vini.lor.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vini.lor.data.remote.AmericasLeaderboardsDataDto

@Dao
interface LorLeaderboardDao {

//    @Query("SELECT * FROM lor_table ORDER BY rank ASC")
//    fun getAllLeaderboard(): PagingSource<Int, AmericasLeaderboardsDataDto>

//    @Query("SELECT * FROM lor_table WHERE name=:nameLeaderboard")
//    fun getSelectedPlayerName(nameLeaderboard: String): AmericasLeaderboardsDataDto
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addLeaderboards(heroes: List<AmericasLeaderboardsDataDto>)
//
//    @Query("DELETE FROM lor_table")
//    suspend fun deleteAllLeaderboards()

}