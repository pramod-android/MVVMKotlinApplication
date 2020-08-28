package com.marathideveloper.mvvmkotlinapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subscriber: Subscriber) :Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) :Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscriber():LiveData<List<Subscriber>>
}