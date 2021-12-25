package com.example.qradmin11.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qradmin11.adapter.LocationAdapter
import com.example.qradmin11.databinding.ActivityUserChatBinding
import com.example.qradmin11.entity.Locationentity
import com.example.qradmin11.viewModels.LocationViewModel

class UserChat : AppCompatActivity(), LocationAdapter.locationClick {

    lateinit var binding: ActivityUserChatBinding

    private val locationViewModel: LocationViewModel by lazy {
        ViewModelProviders.of(this).get(
            LocationViewModel::class.java
        )
    }

    private val locationAdapter: LocationAdapter by lazy { LocationAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Foydalanuvchi bilan Chat"
        super.onCreate(savedInstanceState)
        binding = ActivityUserChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        locationViewModel.readLocation()
        showUser()
    }

    private fun showUser() {
        binding.selectChatUser.adapter = locationAdapter
        binding.selectChatUser.layoutManager = LinearLayoutManager(this)
        locationViewModel.location.observe(this, Observer {
            locationAdapter.setdata(it as MutableList<Locationentity>)
        })
    }

    override fun onclik(user: Locationentity) {
        val intent = Intent(this, SelectUserChatting::class.java)
        intent.putExtra("login", user.login.toString())
        startActivity(intent)
    }
}