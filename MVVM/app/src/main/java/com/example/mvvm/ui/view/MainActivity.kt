package com.example.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.models.User
import com.example.mvvm.ui.adapter.UsersAdapter
import com.example.mvvm.ui.viewmodel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val vm by lazy {
        ViewModelProvider(this).get(GithubViewModel::class.java)
    }

    private val list = arrayListOf<User>()
    private val adapter = UsersAdapter(list)
    private val originalList = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        // Code for searchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // To be used when you've written everything in searchView and then you're pressing submit button
                query?.let { findUsers(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // As soon as you're writing something in searchView, you'll be making a network call. For suggestions.
                newText?.let { findUsers(it) }
                return true// true means you want to achieve this functionality
            }
        })

        // To activate close button in search view. After finish searching a username in search view, when we click on
        // close icon of searchView, the list of users reappears
        searchView.setOnCloseListener {
            list.clear()

            list.addAll(originalList)
            // OR
//            list.addAll(vm.users.value) // This is getting a static value from the list of users(MutableLiveData)

            adapter.notifyDataSetChanged()
            return@setOnCloseListener true
        }

        vm.fetchUsers()

        vm.users.observe(this, Observer {
            // Inflate the list using adapter. To be continued
            if (!it.isNullOrEmpty()) {
                list.addAll(it)
                originalList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

    }

    private fun findUsers(query: String) {
        // we can observe searchUsers function bcoz it returns a LiveData
        vm.searchUsers(query).observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

}