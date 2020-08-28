package com.marathideveloper.mvvmkotlinapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.marathideveloper.mvvmkotlinapplication.databinding.RecyclerViewItemBinding
import com.marathideveloper.mvvmkotlinapplication.db.Subscriber

class SubscriberAdapter(private val subscribers: List<Subscriber>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: RecyclerViewItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.recycler_view_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribers[position])
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

}

class MyViewHolder(val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subscriber: Subscriber) {
        binding.textViewName.text = subscriber.name
        binding.textViewEmail.text = subscriber.email
    }
}