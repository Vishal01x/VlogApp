package com.exa.android.vlogassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [VlogEntity::class],
    version = 1
)
abstract class VlogDatabase: RoomDatabase() {

    abstract val dao: VlogDao
}