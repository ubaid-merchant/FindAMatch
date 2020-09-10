package com.ubaidmerchant.findamatch.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ubaidmerchant.findamatch.data.repo.ResultsRepository
import com.ubaidmerchant.findamatch.model.ResultsModel
import com.ubaidmerchant.findamatch.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for [MainActivity]
 */
@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(private val resultsRepository: ResultsRepository) :
    ViewModel() {

    private val _resultsLiveData = MutableLiveData<State<List<ResultsModel>>>()

    val resultsLiveData: LiveData<State<List<ResultsModel>>>
        get() = _resultsLiveData

    fun getResults() {
        viewModelScope.launch {
            resultsRepository.getAllResults().collect {
                _resultsLiveData.value = it
            }
        }
    }

    fun updateStatus(result: ResultsModel, selected: Boolean) {
        val status = if (selected) "Accepted" else "Declined"
        viewModelScope.async {
            resultsRepository.updateStatus(result.copy(isSelected = selected, status = status))
        }
    }
}