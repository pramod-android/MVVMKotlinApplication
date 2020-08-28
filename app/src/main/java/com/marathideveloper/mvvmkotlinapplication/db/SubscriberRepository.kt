package com.marathideveloper.mvvmkotlinapplication.db

class SubscriberRepository (private val dao : SubscriberDao){
    val subscribers = dao.getAllSubscriber()

    suspend fun insertSubscriber(subscriber: Subscriber) : Long{
       return dao.insertSubscriber(subscriber)
    }

    suspend fun updateSubscriber(subscriber: Subscriber) : Int{
        return dao.updateSubscriber(subscriber)
    }
    suspend fun deleteSubscriber(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }
    suspend fun deleteAllSubscriber(){
        dao.deleteAll()
    }
}