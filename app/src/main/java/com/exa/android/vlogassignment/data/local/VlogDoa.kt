package com.exa.android.vlogassignment.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface VlogDao {
    @Upsert
    suspend fun upsertAll(vlogs : List<VlogEntity>)

    @Query("SELECT * FROM vlogentity")
    fun pagingSource(): PagingSource<Int, VlogEntity>

    @Query("DELETE FROM vlogentity")
    suspend fun clearAll()
}