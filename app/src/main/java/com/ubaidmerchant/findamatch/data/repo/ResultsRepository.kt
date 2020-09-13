package com.ubaidmerchant.findamatch.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ubaidmerchant.findamatch.data.local.dao.ResultsDao
import com.ubaidmerchant.findamatch.data.remote.api.FamService
import com.ubaidmerchant.findamatch.data.remote.api.FamService.Companion.RESULTS_COUNT
import com.ubaidmerchant.findamatch.model.ResultsModel
import com.ubaidmerchant.findamatch.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */
@ExperimentalCoroutinesApi
@Singleton
class ResultsRepository @Inject constructor(
    private val resultsDao: ResultsDao,
    private val famService: FamService
) {
    private var resultsList: MutableLiveData<State<List<ResultsModel>>> = MutableLiveData()

    init {
        resultsList.value = State.loading()
    }

    suspend fun getAllResults(): MutableLiveData<State<List<ResultsModel>>> {
        val allResults = resultsDao.getAllResults()
        if (allResults.isNotEmpty()) {
            resultsList.value = State.success(allResults)
            Log.d("getAllResults", "local")
        } else {
            try {
                val response = withContext(Dispatchers.IO) {
                    famService.getResults(RESULTS_COUNT)
                }
                try {
                    if (response.isSuccessful) {
                        val resp = response.body()
                        Log.d("getResults", resp?.results.toString())
                        withContext(Dispatchers.IO) {
                            resp?.results?.forEach {
                                resultsDao.insertResult(it)
                            }
                        }
                        resp?.results?.let {
                            resultsList.value = State.success(it.toList())
                            Log.d("getResults", "server")
                        }
                    } else {
                        resultsList.postValue(State.error(response.message()))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    resultsList.value = State.error(e.message.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                resultsList.value = State.error(e.message.toString())
            }
        }

        return resultsList
    }

    suspend fun updateResult(result: ResultsModel): MutableLiveData<State<List<ResultsModel>>> {
        withContext(Dispatchers.IO) {
            resultsDao.updateResult(result)
        }
        resultsList.value = State.success(resultsDao.getAllResults())
        return resultsList
    }
}