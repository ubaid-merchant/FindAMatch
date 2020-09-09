package com.ubaidmerchant.findamatch.data.repo

import androidx.annotation.MainThread
import com.ubaidmerchant.findamatch.data.local.dao.ResultsDao
import com.ubaidmerchant.findamatch.data.remote.api.FamService
import com.ubaidmerchant.findamatch.data.remote.api.FamService.Companion.RESULTS_COUNT
import com.ubaidmerchant.findamatch.model.ResponseModel
import com.ubaidmerchant.findamatch.model.ResultsModel
import com.ubaidmerchant.findamatch.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
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

    /**
     * Fetched the posts from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    fun getAllResults(): Flow<State<List<ResultsModel>>> {
        return object : NetworkBoundRepository<List<ResultsModel>, ResponseModel>() {

            override suspend fun saveRemoteData(response: ResponseModel) =
                resultsDao.insertResults(response.results)

            override fun fetchFromLocal(): Flow<List<ResultsModel>> = resultsDao.getAllResults()

            override suspend fun fetchFromRemote(): Response<ResponseModel> =
                famService.getResults(RESULTS_COUNT)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    /**
     * Retrieves a result with specified [emailId].
     * @param emailId Unique id of a [ResultsModel].
     * @return [ResultsModel] data fetched from the database.
     */
    @MainThread
    fun getResultById(emailId: String): Flow<ResultsModel> = resultsDao.getResultById(emailId)
}