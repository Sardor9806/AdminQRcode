package com.example.qradmin11.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qradmin11.databinding.ActivityUserChatBinding

class UserChat : AppCompatActivity() {
    lateinit var binding: ActivityUserChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        title="Foydalanuvchi bilan Chat"
        super.onCreate(savedInstanceState)
        binding= ActivityUserChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}