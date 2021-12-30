package com.example.qradmin11.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qradmin11.R
import com.example.qradmin11.adapter.UserAdapter
import com.example.qradmin11.databinding.ActivityUserAddBinding
import com.example.qradmin11.databinding.AddUserDialogBinding
import com.example.qradmin11.entity.Locationentity
import com.example.qradmin11.entity.User
import com.example.qradmin11.entity.UserChatAddEntity
import com.example.qradmin11.viewModels.LocationViewModel
import com.example.qradmin11.viewModels.UserChatViewModel
import com.example.qradmin11.viewModels.UserViewModel

class UserAdd : AppCompatActivity(), UserAdapter.RecyclerOnClikListener {

    lateinit var binding: ActivityUserAddBinding

    private val userViewModel: UserViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    private val adapter: UserAdapter by lazy { UserAdapter(this) }

    private val locationViewModel: LocationViewModel by lazy {
        ViewModelProviders.of(this).get(LocationViewModel::class.java)
    }

    private val userChatViewModel: UserChatViewModel by lazy {
        ViewModelProviders.of(this).get(UserChatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Foydalanuvchi Qo`shish"
        super.onCreate(savedInstanceState)
        binding = ActivityUserAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel.readUser()
        locationViewModel.readLocation()
        addUser()

        readUsers()
    }

    // bazadagi foydalanuvchilarni olib keladi
    private fun readUsers() {
        binding.listUser.adapter = adapter
        binding.listUser.layoutManager = LinearLayoutManager(this)
        userViewModel.users.observe(this, Observer {
            adapter.setdata(it)
        })
    }

    // bazaga foydalanuvchilarni qo`shadi
    private fun addUser() {
        binding.fab.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            val view = LayoutInflater.from(this).inflate(R.layout.add_user_dialog, null)
            alertDialog.setView(view)
            alertDialog.setTitle("Foydalanuvchi qo`shish")
            val bind = AddUserDialogBinding.bind(view)
            alertDialog.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                if (bind.addLogin.text.toString() != "" && bind.addParol.text.toString() != "") {
                    userViewModel.insertUser(
                        User(
                            login = bind.addLogin.text.toString(),
                            password = bind.addParol.text.toString()
                        )
                    )
                    locationViewModel.insertLocation(Locationentity(login = bind.addLogin.text.toString()))
                }
            }
            alertDialog.show()
        }
    }

    //foydalanuvchini o`chiradi
    override fun onclik(user: User) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(user.login)
        alertDialog.setMessage("O`chirasizmi?")
        alertDialog.setPositiveButton("Ha") { dialogInterface: DialogInterface, i: Int ->
            userViewModel.deleteUser(user)
            locationViewModel.location.observe(this, Observer {
                it.forEach {
                    if(it.login==user.login)
                    {
                        locationViewModel.deleteLocation(it)
                    }
                }
            })


        }
        alertDialog.setNegativeButton("Yo`q") { dialogInterface: DialogInterface, i: Int -> }
        alertDialog.show()
    }
}