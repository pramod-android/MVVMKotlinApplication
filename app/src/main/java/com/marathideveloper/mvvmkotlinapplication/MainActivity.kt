package com.marathideveloper.mvvmkotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marathideveloper.mvvmkotlinapplication.databinding.ActivityMainBinding
import com.marathideveloper.mvvmkotlinapplication.db.Subscriber
import com.marathideveloper.mvvmkotlinapplication.db.SubscriberDatabase
import com.marathideveloper.mvvmkotlinapplication.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriverViewModel: SubscriverViewModel
    private lateinit var adapter: SubscriberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriverViewModel = ViewModelProvider(this, factory).get(SubscriverViewModel::class.java)
        binding.myViewModel = subscriverViewModel

        binding.lifecycleOwner = this

        initRecyclerView()

        subscriverViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displaySubscriberList() {
        subscriverViewModel.subscribers.observe(this, Observer {
            Log.i("MyTag", it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
//            binding.recyclerViewSubscribers.adapter = SubscriberAdapter(it,
//                { selectedItem: Subscriber -> recyclerItemClicked(selectedItem) })
        })
    }

    fun initRecyclerView() {
        binding.recyclerViewSubscribers.layoutManager = LinearLayoutManager(this)
        adapter = SubscriberAdapter ({selectedItem  :Subscriber -> recyclerItemClicked(selectedItem) })
        binding.recyclerViewSubscribers.adapter = adapter
//        binding.recyclerViewSubscribers.adapter = SubscriberAdapter(it,
//            { selectedItem: Subscriber -> recyclerItemClicked(selectedItem) })
        displaySubscriberList()
    }

    private fun recyclerItemClicked(subscriber: Subscriber) {
        Toast.makeText(this, "Selected subscriber is ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscriverViewModel.initUpdateAndDelete(subscriber)
    }
}