package com.marathideveloper.mvvmkotlinapplication.db

class SubscriberRepository (private val dao : SubscriberDao){
    val subscribers = dao.getAllSubscriber()

    suspend fun insertSubscriber(subscriber: Subscriber){
        dao.insertSubscriber(subscriber)
    }

    suspend fun updateSubscriber(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }
    suspend fun deleteSubscriber(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }
    suspend fun deleteAllSubscriber(){
        dao.deleteAll()
    }
}