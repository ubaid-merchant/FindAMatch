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
     * Inserts [results] into the [ResultsModel.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param results ResultsModel
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResults(results: ArrayList<ResultsModel>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateResults(vararg result: ResultsModel)

    /**
     * Deletes all the results from the [ResultsModel.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${ResultsModel.TABLE_NAME}")
    fun deleteAllResults()

    /**
     * Fetches the result from the [ResultsModel.TABLE_NAME] table whose id is [emailId].
     * @param emailId Unique ID of [ResultsModel]
     * @return [Flow] of [ResultsModel] from database table.
     */
    @Query("SELECT * FROM ${ResultsModel.TABLE_NAME} WHERE EMAIL = :emailId")
    fun getResultById(emailId: String): Flow<ResultsModel>

    /**
     * Fetches all the results from the [ResultsModel.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${ResultsModel.TABLE_NAME}")
    fun getAllResults(): Flow<List<ResultsModel>>
}