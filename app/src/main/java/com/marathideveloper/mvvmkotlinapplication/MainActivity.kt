package com.marathideveloper.mvvmkotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marathideveloper.mvvmkotlinapplication.databinding.ActivityMainBinding
import com.marathideveloper.mvvmkotlinapplication.db.Subscriber
import com.marathideveloper.mvvmkotlinapplication.db.SubscriberDatabase
import com.marathideveloper.mvvmkotlinapplication.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var subscriverViewModel: SubscriverViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //setContentView(R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriverViewModel = ViewModelProvider(this,factory).get(SubscriverViewModel::class.java)
        binding.myViewModel = subscriverViewModel

        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private  fun displaySubscriberList(){
        subscriverViewModel.subscribers.observe(this, Observer {
            Log.i("MyTag",it.toString())
            binding.recyclerViewSubscribers.adapter = SubscriberAdapter(it)
        })
    }

   fun initRecyclerView(){
       binding.recyclerViewSubscribers.layoutManager = LinearLayoutManager(this)
       displaySubscriberList()
   }
}