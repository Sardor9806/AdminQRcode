package com.example.qradmin11.activity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.qradmin11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        locationgaRuxsat()
        domenQush()
        userQush()
      //  userQayerda()
        userChat()
    }

    private fun locationgaRuxsat() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
    }

    private fun userChat() {
        binding.userChatting.setOnClickListener {
            startActivity(Intent(this,UserChat::class.java))
        }

    }

    private fun userQayerda() {
//        binding.userQayerda.setOnClickListener {
//            startActivity(Intent(this,UserQayerda::class.java))
//        }
    }

    private fun userQush() {
        binding.userQush.setOnClickListener {
            startActivity(Intent(this,UserAdd::class.java))
        }

    }

    private fun domenQush() {
        binding.domenQush.setOnClickListener {
            startActivity(Intent(this,DomenAdd::class.java))
        }

    }
}