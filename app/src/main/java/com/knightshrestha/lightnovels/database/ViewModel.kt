package com.knightshrestha.lightnovels.database
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ViewModel  (application: Application) : AndroidViewModel(application) {
    private val repository: REPO
    val allBooks: LiveData<List<SeriesItem>>
    val itemCount: LiveData<ItemCount>


    init {
        val dao = MyDatabase.getDatabase(application).dao()
        repository = REPO(dao)
        allBooks = repository.getAllSeries()
        itemCount = repository.getAllItemCount()
    }

    fun insertLocalItem(seriesItem: SeriesItem) {
        viewModelScope.launch {
            repository.insertSeries(seriesItem)
        }
    }

    suspend fun getLocalSeries(seriesID: Int): SeriesItem? {
        val deferred: Deferred<SeriesItem?> = viewModelScope.async {
            repository.getOneSeries(seriesID)
        }
        return deferred.await()
    }

    fun updateLocalItem(seriesItem: SeriesItem) {
        viewModelScope.launch {
            repository.updateSeries(seriesItem)
        }
    }
}