package com.knightshrestha.lightnovels.database

import androidx.lifecycle.LiveData

class REPO (private val dao: DAO) {
    fun getAllSeries(): LiveData<List<SeriesItem>> {
        return dao.getAllItems()
    }

    fun getAllItemCount(): LiveData<ItemCount> {
        return dao.getAllItemCount()
    }

    suspend fun updateSeries(seriesItem: SeriesItem) {
        dao.updateWithTimestamp(seriesItem)
    }

    suspend fun insertSeries(seriesItem: SeriesItem) {
        dao.insertWithTimestamp(seriesItem)
    }

    suspend fun getOneSeries(seriesID: Int): SeriesItem? {
        return dao.getOneSeries(seriesID)
    }


}