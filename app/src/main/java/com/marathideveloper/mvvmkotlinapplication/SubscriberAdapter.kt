package com.marathideveloper.mvvmkotlinapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.marathideveloper.mvvmkotlinapplication.databinding.RecyclerViewItemBinding
import com.marathideveloper.mvvmkotlinapplication.db.Subscriber
import com.marathideveloper.mvvmkotlinapplication.generated.callback.OnClickListener

class SubscriberAdapter(

    private val clickListener: (Subscriber) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {
    private val subscribers = ArrayList<Subscriber>()
    fun setList(subscriberList: List<Subscriber>){
        subscribers.clear()
        subscribers.addAll(subscriberList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: RecyclerViewItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.recycler_view_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribers[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

}

class MyViewHolder(val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        binding.textViewName.text = subscriber.name
        binding.textViewEmail.text = subscriber.email
        binding.listItemId.setOnClickListener {
            clickListener(subscriber)
        }
    }
}