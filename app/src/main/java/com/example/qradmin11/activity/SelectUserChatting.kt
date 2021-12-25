package com.example.qradmin11.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.qradmin11.R
import com.example.qradmin11.databinding.ActivitySelectUserChattingBinding
import com.example.qradmin11.entity.UserChatAddEntity
import com.example.qradmin11.viewModels.UserChatViewModel

class SelectUserChatting : AppCompatActivity() {


    lateinit var binding: ActivitySelectUserChattingBinding

    private val userChatViewModel: UserChatViewModel by lazy {
        ViewModelProviders.of(this).get(UserChatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectUserChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = intent.getStringExtra("login")
        userChatViewModel.readLocation(intent.getStringExtra("login").toString())
        readMessage()
        sendMessage()
    }

    private fun readMessage() {
            userChatViewModel.message.observe(this, Observer {
                it.forEach {
                    d("sardor",it.admin)
                }
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
        }
    }
}