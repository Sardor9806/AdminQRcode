package com.example.qradmin11.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qradmin11.R
import com.example.qradmin11.entity.UserChatAddEntity
import com.google.android.material.card.MaterialCardView


class MessageAdapter(val context:Context,val messageList:ArrayList<UserChatAddEntity>,val listener:MessageSetOnClickListener):RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    val admindan=1
    val userdan=2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==1)
        {
            return SendViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.send_message, parent, false))

        }
        else
        {
            return ComeViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.come_message, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val currentMessage=messageList[position]

        if(holder.javaClass==SendViewHolder::class.java)
        {
            val viewHolder = holder as SendViewHolder
            viewHolder.userMessage.text=currentMessage.admin
            viewHolder.delete= currentMessage.login_chat.toString()
            if(currentMessage.message_status=="seen")
            {
                viewHolder.seen.visibility=View.VISIBLE
                viewHolder.not_seen.visibility=View.GONE
            }else
            {
                viewHolder.seen.visibility=View.GONE
                viewHolder.not_seen.visibility=View.VISIBLE
            }
        }
        else{
            val viewHolder = holder as ComeViewHolder
            viewHolder.adminMessage.text=currentMessage.user
            viewHolder.delete= currentMessage.login_chat.toString()

        }


    }

    override fun getItemCount(): Int = messageList.size

    override fun getItemViewType(position: Int): Int {
        val currentMessage=messageList[position]
        return if(currentMessage.admin.isNotEmpty()) {
            admindan
        } else {
            userdan
        }
    }
   inner class SendViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val userMessage = itemview.findViewById<TextView>(R.id.send_message_new)
       val seen=itemview.findViewById<MaterialCardView>(R.id.see)
       val not_seen=itemview.findViewById<MaterialCardView>(R.id.not_see)
        var delete:String=""
        init {
            itemview.setOnClickListener {
                listener.listener(delete)
            }
        }
    }
   inner class ComeViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val adminMessage=itemview.findViewById<TextView>(R.id.come_message_new)

       var delete:String=""
       init {
           itemview.setOnClickListener {
               listener.listener(delete)
           }

       }
    }
    interface MessageSetOnClickListener{
        fun listener(userChatAddEntity: String)
    }
}