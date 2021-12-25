package com.example.qradmin11.adapter

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qradmin11.R
import com.example.qradmin11.databinding.ItemUserChattingBinding
import com.example.qradmin11.entity.UserChatAddEntity


class UserChattingAdapter : RecyclerView.Adapter<UserChattingAdapter.ViewHolder>() {

    private var messageList = mutableListOf<UserChatAddEntity>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemUserChattingBinding.bind(itemView)
        fun bind(curentMessage: UserChatAddEntity) {
            if(curentMessage.admin.isNotEmpty())
            {
                binding.sendMessage.text=curentMessage.admin
                binding.comeMessageLayout.visibility=View.GONE
            }else
            {
                binding.comeMessage.text=curentMessage.user
                binding.sendMessageLayout.visibility=View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_chatting, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int = messageList.size

    fun sendData(list: MutableList<UserChatAddEntity>) {
        messageList=list
        notifyDataSetChanged()
        notifyItemInserted(messageList.size)
    }

}