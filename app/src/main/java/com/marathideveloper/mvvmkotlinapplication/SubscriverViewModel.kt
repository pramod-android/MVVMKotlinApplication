package com.marathideveloper.mvvmkotlinapplication

import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.marathideveloper.mvvmkotlinapplication.db.Subscriber
import com.marathideveloper.mvvmkotlinapplication.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SubscriverViewModel(private val repository: SubscriberRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>> get() =statusMessage

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

        if(inputName.value==null){
            statusMessage.value = Event("Please enter name")
        }else if(inputEmail.value == null){
            statusMessage.value = Event("Please enter email")
        }
//        else if(Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()){
//            statusMessage.value = Event("Please enter correct email address")
//        }
        else {

            if (isUpdateOrDelete) {
                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.name = inputName.value!!
                update(subscriberToUpdateOrDelete)
            } else {

                val name = inputName.value!!
                val email = inputEmail.value!!

                insert(Subscriber(0, name, email))
                inputName.value = null
                inputEmail.value = null
            }
        }
    }

    fun clearAllOrDelete() {

        if (isUpdateOrDelete) {
            delete(subscriberToUpdateOrDelete)
        } else {
            deleteAll()
        }
    }

    fun insert(subscriber: Subscriber): Job = viewModelScope.launch {
        val newRowId:Long = repository.insertSubscriber(subscriber)
        if(newRowId>-1){
            statusMessage.value = Event("Inserted Successfully ${newRowId}")

        }else{
            statusMessage.value = Event("error occurred ${newRowId}")

        }
       // repository.insertSubscriber(subscriber)
    }

    fun update(subscriber: Subscriber): Job = viewModelScope.launch {

        val noOfRows=repository.updateSubscriber(subscriber)
        if(noOfRows>0) {
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            subscriberToUpdateOrDelete = subscriber
            saveUpdateButtonText.value = "Save"
            clearAllDelateButtonText.value = "Clear All"
            statusMessage.value = Event("Updated Successfully ${noOfRows}")
        }else{
            statusMessage.value = Event("error occurred ${noOfRows}")

        }

    }

    fun delete(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.deleteSubscriber(subscriber)

        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        subscriberToUpdateOrDelete = subscriber
        saveUpdateButtonText.value = "Save"
        clearAllDelateButtonText.value = "Clear All"

        statusMessage.value = Event("Updated Successfully")


    }

    fun deleteAll(): Job = viewModelScope.launch {
        repository.deleteAllSubscriber()

        statusMessage.value = Event("Cleared Successfully")

    }

    fun initUpdateAndDelete(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveUpdateButtonText.value = "Update"
        clearAllDelateButtonText.value = "Delete"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        //TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        //   TODO("Not yet implemented")
    }

}