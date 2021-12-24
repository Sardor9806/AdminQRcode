package com.example.qradmin11.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qradmin11.googleMap.MapsActivity
import com.example.qradmin11.adapter.LocationAdapter
import com.example.qradmin11.databinding.ActivityUserQayerdaBinding

import com.example.qradmin11.entity.Locationentity
import com.example.qradmin11.viewModels.LocationViewModel

class UserQayerda : AppCompatActivity(), LocationAdapter.locationClick {

    lateinit var binding: ActivityUserQayerdaBinding

    private val locationViewModel: LocationViewModel by lazy { ViewModelProviders.of(this).get(LocationViewModel::class.java) }

    private val locationAdapter:LocationAdapter by lazy { LocationAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        title="Foydalanuvchi qayerda?"
        super.onCreate(savedInstanceState)
        binding= ActivityUserQayerdaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        locationViewModel.readLocation()
        showUser()
    }

    private fun showUser() {
        binding.locationUser.adapter=locationAdapter
        binding.locationUser.layoutManager=LinearLayoutManager(this)
        locationViewModel.location.observe(this, Observer {
            locationAdapter.setdata(it as MutableList<Locationentity>)
        })
    }

    override fun onclik(user: Locationentity) {
        val intent=Intent(this,MapsActivity::class.java)
        intent.putExtra("x",user.x)
        intent.putExtra("y",user.y)
        startActivity(intent)
    }
}