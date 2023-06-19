package com.knightshrestha.lightnovels.remotedatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewFactory(private val myRepo: MyRepo): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(myRepo) as T
    }
}