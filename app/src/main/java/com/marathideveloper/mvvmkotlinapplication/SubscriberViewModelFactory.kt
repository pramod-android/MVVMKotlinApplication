package com.marathideveloper.mvvmkotlinapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marathideveloper.mvvmkotlinapplication.db.SubscriberRepository

class SubscriberViewModelFactory(private  val repository: SubscriberRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
if(modelClass.isAssignableFrom(SubscriverViewModel::class.java)){
return  SubscriverViewModel(repository) as T
}
        throw  IllegalArgumentException("unknown view model class")
    }

}