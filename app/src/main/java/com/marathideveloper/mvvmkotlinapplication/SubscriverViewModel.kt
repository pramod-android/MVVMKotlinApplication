package com.marathideveloper.mvvmkotlinapplication

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marathideveloper.mvvmkotlinapplication.db.Subscriber
import com.marathideveloper.mvvmkotlinapplication.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriverViewModel(private val repository: SubscriberRepository) : ViewModel(),Observable {

    val subscribers = repository.subscribers

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllDelateButtonText = MutableLiveData<String>()

    init {
        saveUpdateButtonText.value = "Save"
        clearAllDelateButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        val name = inputName.value !!
        val email = inputEmail.value!!

        insert(Subscriber(0,name,email))
        inputName.value = null
        inputEmail.value = null
    }

    fun clearAllOrDelete() {

        deleteAll()
    }

    fun insert(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.insertSubscriber(subscriber)
    }

    fun update(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.updateSubscriber(subscriber)
    }

    fun delete(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.deleteSubscriber(subscriber)
    }

    fun deleteAll(): Job = viewModelScope.launch {
        repository.deleteAllSubscriber()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        //TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
     //   TODO("Not yet implemented")
    }

}