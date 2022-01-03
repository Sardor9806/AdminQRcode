package com.example.qradmin11.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qradmin11.adapter.LocationAdapter
import com.example.qradmin11.adapter.UserAdapter
import com.example.qradmin11.databinding.ActivityUserChatBinding
import com.example.qradmin11.entity.Locationentity
import com.example.qradmin11.entity.User
import com.example.qradmin11.viewModels.UserViewModel

class UserChat : AppCompatActivity(),UserAdapter.RecyclerOnClikListener {

    lateinit var binding: ActivityUserChatBinding

    private val user: UserViewModel by lazy {
        ViewModelProviders.of(this).get(
            UserViewModel::class.java
        )
    }

    private val userAdapter: UserAdapter by lazy { UserAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Foydalanuvchi bilan Chat"
        super.onCreate(savedInstanceState)
        binding = ActivityUserChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user.readUser()
        showUser()
    }

    private fun showUser() {
        binding.selectChatUser.adapter = userAdapter
        binding.selectChatUser.layoutManager = LinearLayoutManager(this)
        user.users.observe(this, Observer {
            userAdapter.setdata(it as List<User>)
        })
    }

    override fun onclik(user: User) {
        val intent = Intent(this, SelectUserChatting::class.java)
        intent.putExtra("login", user.login.toString())
        startActivity(intent)
    }
}