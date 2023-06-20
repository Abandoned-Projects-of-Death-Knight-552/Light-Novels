package com.knightshrestha.lightnovels.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


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

    @Query("SELECT * FROM `Series Items` WHERE seriesID IS :seriesId")
    abstract suspend fun getOne(seriesId: Int): SeriesItem?
    suspend fun getOneSeries(seriesId: Int): SeriesItem? {
        return getOne(seriesId)
    }

    @Query("SELECT COUNT(*) AS totalRows, COUNT(CASE WHEN 'isInLibrary' = '1' THEN 2 END) AS inLibrary FROM `Series Items`")
    abstract fun getItemCount(): LiveData<ItemCount>
    fun getAllItemCount(): LiveData<ItemCount> {
        return getItemCount()
    }

}