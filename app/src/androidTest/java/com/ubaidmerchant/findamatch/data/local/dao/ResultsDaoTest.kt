package com.ubaidmerchant.findamatch.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ubaidmerchant.findamatch.data.local.FamResultsDatabase
import com.ubaidmerchant.findamatch.model.ResultsModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResultsDaoTest {
    private lateinit var mDatabase: FamResultsDatabase

    @Before
    fun init() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FamResultsDatabase::class.java
        ).build()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insert_and_select_results() = runBlocking {
        val results = listOf(
            ResultsModel(
                gender = "male",
                email = "male@male.com",
                phone = "1234567890",
                cell = "9012345678",
                nat = "NL"
            ),
            ResultsModel(
                gender = "female",
                email = "female@female.com",
                phone = "9012345678",
                cell = "1234567890",
                nat = "IR"
            )
        )

        results.forEach {
            mDatabase.getResultsDao().insertResult(it)
        }

        val dbResults = mDatabase.getResultsDao().getAllResultsAsFlow().toList()[0]

        assertThat(dbResults, equalTo(results))
    }

    @Test
    @Throws(InterruptedException::class)
    fun select_result_by_email() = runBlocking {
        val results = listOf(
            ResultsModel(
                gender = "male",
                email = "male@male.com",
                phone = "1234567890",
                cell = "9012345678",
                nat = "NL"
            ),
            ResultsModel(
                gender = "female",
                email = "female@female.com",
                phone = "9012345678",
                cell = "1234567890",
                nat = "IR"
            )
        )

        results.forEach {
            mDatabase.getResultsDao().insertResult(it)
        }

        var dbResult = mDatabase.getResultsDao().getResultById("male@male.com")
        assertThat(dbResult.toList()[0], equalTo(results[0]))

        dbResult = mDatabase.getResultsDao().getResultById("female@female.com")
        assertThat(dbResult.toList()[1], equalTo(results[1]))
    }

    @After
    fun cleanup() {
        mDatabase.close()
    }
}
