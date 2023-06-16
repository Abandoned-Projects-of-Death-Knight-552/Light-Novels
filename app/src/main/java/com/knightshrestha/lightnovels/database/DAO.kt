package com.knightshrestha.lightnovels.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.knightshrestha.lightnovels.database.SeriesItem


@Dao
abstract class DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun  insert(seriesItem: SeriesItem)
    suspend fun insertWithTimestamp(seriesItem: SeriesItem) {
        insert(seriesItem.apply{
            createdAt = System.currentTimeMillis()
            updatedAt = System.currentTimeMillis()
        })
    }


    @Update
    abstract suspend fun update(seriesItem: SeriesItem)
    suspend fun updateWithTimestamp(seriesItem: SeriesItem) {
        insert(seriesItem.apply{
            updatedAt = System.currentTimeMillis()
        })
    }

    @Query("SELECT * FROM `Series Items`")
    abstract fun getAll(): LiveData<List<SeriesItem>>
    fun getAllItems(): LiveData<List<SeriesItem>> {
        return getAll()
    }
}