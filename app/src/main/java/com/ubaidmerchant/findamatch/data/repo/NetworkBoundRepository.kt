package com.ubaidmerchant.findamatch.data.repo

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.ubaidmerchant.findamatch.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
 * A repository which provides resource from local database as well as remote end point.
 *
 * [RESULT] represents the type for database.
 * [REQUEST] represents the type for network.
 */
@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow<State<RESULT>> {

        // Emit Loading State
        emit(State.loading())

        // Emit Database content first
        emit(State.success(fetchFromLocal().first()))

        // Fetch latest results from remote
        val apiResponse = fetchFromRemote()

        // Parse body
        val remoteResults = apiResponse.body()

        // Check for response validation
        if (apiResponse.isSuccessful && remoteResults != null) {
            // Save posts into the persistence storage
            saveRemoteData(remoteResults)
        } else {
            // Something went wrong! Emit Error state.
            emit(State.error(apiResponse.message()))
        }

        // Retrieve results from persistence storage and emit
        emitAll(
            fetchFromLocal().map {
                State.success<RESULT>(it)
            }
        )
    }.catch { e ->
        // Exception occurred! Emit error
        emit(State.error("Network error! Can't get latest results."))
        e.printStackTrace()
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}