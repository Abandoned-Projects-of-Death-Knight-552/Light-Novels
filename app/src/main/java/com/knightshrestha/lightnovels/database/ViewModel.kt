package com.knightshrestha.lightnovels.database
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel  (application: Application) : AndroidViewModel(application) {
    private val repository: REPO
    val allBooks: LiveData<List<SeriesItem>>
    val specificBook: MutableLiveData<SeriesItem> = MutableLiveData()

    init {
        val dao = MyDatabase.getDatabase(application).dao()
        repository = REPO(dao)
        allBooks = repository.getAllSeries()
    }

    fun insertLocalItem(seriesItem: SeriesItem) {
        viewModelScope.launch {
            repository.insertSeries(seriesItem)
        }
    }

    fun getLocalSeries(seriesID: Int) {
        viewModelScope.launch {
            specificBook.value = repository.getOneSeries(seriesID)
        }
    }

    fun updateLocalItem(seriesItem: SeriesItem) {
        viewModelScope.launch {
            repository.updateSeries(seriesItem)
        }
    }
}