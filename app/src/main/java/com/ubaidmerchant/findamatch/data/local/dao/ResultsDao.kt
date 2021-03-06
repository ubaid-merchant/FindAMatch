package com.ubaidmerchant.findamatch.data.local.dao

import androidx.room.*
import com.ubaidmerchant.findamatch.model.ResultsModel
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for [com.ubaidmerchant.findamatch.data.local.FamResultsDatabase]
 */
@Dao
interface ResultsDao {

    /**
     * Inserts [result] into the [ResultsModel.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param result ResultsModel
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: ResultsModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateResult(result: ResultsModel)

    /**
     * Deletes all the results from the [ResultsModel.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${ResultsModel.TABLE_NAME}")
    suspend fun deleteAllResults()

    /**
     * Fetches all the results from the [ResultsModel.TABLE_NAME] table.
     * @return [List]
     */
    @Query("SELECT * FROM ${ResultsModel.TABLE_NAME}")
    suspend fun getAllResults(): List<ResultsModel>

    /**
     * Fetches all the results from the [ResultsModel.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${ResultsModel.TABLE_NAME}")
    fun getAllResultsAsFlow(): Flow<List<ResultsModel>>

    /**
     * Fetches the result from the [ResultsModel.TABLE_NAME] table whose id is [emailId].
     * @param emailId Unique ID of [ResultsModel]
     * @return [Flow] of [ResultsModel] from database table.
     */
    @Query("SELECT * FROM ${ResultsModel.TABLE_NAME} WHERE EMAIL = :emailId")
    fun getResultById(emailId: String): Flow<ResultsModel>

}