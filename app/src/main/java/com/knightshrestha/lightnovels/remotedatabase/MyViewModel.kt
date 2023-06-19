package com.knightshrestha.lightnovels.remotedatabase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knightshrestha.lightnovels.remotedatabase.dataclass.NeonResponse
import kotlinx.coroutines.launch

class MyViewModel(private val repo: MyRepo): ViewModel() {
    val neonData: MutableLiveData<NeonResponse> = MutableLiveData()
    fun fetchAllData() {
        viewModelScope.launch {
            val response = repo.fetchAllData()
            neonData.value = response
        }
    }
}