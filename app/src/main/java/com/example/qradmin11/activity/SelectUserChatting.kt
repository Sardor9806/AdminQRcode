package com.example.qradmin11.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qradmin11.adapter.MessageAdapter
import com.example.qradmin11.databinding.ActivitySelectUserChattingBinding
import com.example.qradmin11.entity.UserChatAddEntity
import com.example.qradmin11.viewModels.UserChatViewModel

class SelectUserChatting : AppCompatActivity(),MessageAdapter.MessageSetOnClickListener {


    lateinit var binding: ActivitySelectUserChattingBinding

    private val userChatViewModel: UserChatViewModel by lazy {
        ViewModelProviders.of(this).get(UserChatViewModel::class.java)
    }

    lateinit var adapter:MessageAdapter

    private var messageArray:ArrayList<UserChatAddEntity> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectUserChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = intent.getStringExtra("login")
        adapter= MessageAdapter(this,messageArray,this)
        userChatViewModel.readLocation(intent.getStringExtra("login").toString())
        readMessage()
        sendMessage()

    }

    private fun readMessage() {

        binding.messageRecyclerView.setHasFixedSize(true)
        binding.messageRecyclerView.adapter=adapter

        binding.messageRecyclerView.layoutManager=LinearLayoutManager(this)
            userChatViewModel.message.observe(this, Observer {
                messageArray.clear()
                it.forEach {
                    messageArray.add(it)
                }
                adapter.notifyDataSetChanged()
                binding.messageRecyclerView.scrollToPosition(messageArray.size-1)
            })
    }

    private fun sendMessage() {
        binding.sendMessageButton.setOnClickListener {
            userChatViewModel.insertChatUser(
                UserChatAddEntity(
                    login_chat = intent.getStringExtra("login"),
                    admin = binding.messageWriteEdt.text.toString()
                )
            )
            binding.messageWriteEdt.text.clear()
        }
    }

    override fun listener(userChatAddEntity: String) {
        val alertDialog=AlertDialog.Builder(this)
        alertDialog.setMessage("Xabarni o`chirmoqchimisiz?")
        alertDialog.setPositiveButton("Ha"){ dialogInterface: DialogInterface, i: Int ->
            userChatViewModel.deleteLocation(userChatAddEntity)
        }
        alertDialog.setNegativeButton("Yo`q"){ dialogInterface: DialogInterface, i: Int -> }
        alertDialog.show()
    }
}