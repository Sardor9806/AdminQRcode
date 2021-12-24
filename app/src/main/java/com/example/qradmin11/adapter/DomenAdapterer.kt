package com.example.qradmin11.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qradmin11.R
import com.example.qradmin11.activity.DomenAdd
import com.example.qradmin11.databinding.ItemDomenBinding
import com.example.qradmin11.entity.DomenEntity


class DomenAdapterer(private val listener: DomenAdd) : RecyclerView.Adapter<DomenAdapterer.ViewHolder>() {


    interface onclikListener{
        fun onclik(user: DomenEntity)
    }
    private var userList:MutableList<DomenEntity> = mutableListOf()

    fun setdata(user: MutableList<DomenEntity>) {
        this.userList = user
        notifyDataSetChanged()
    }
    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding = ItemDomenBinding.bind(itemView)
        fun bind(user: DomenEntity) {
            binding.domen.text=user.domenName
            itemView.setOnClickListener {
                listener.onclik(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_domen, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

}