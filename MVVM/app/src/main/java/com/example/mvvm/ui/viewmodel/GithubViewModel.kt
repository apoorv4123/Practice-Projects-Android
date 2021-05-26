package com.example.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.models.User
import com.example.mvvm.data.repos.GithubRepository
import kotlinx.coroutines.*

class GithubViewModel : ViewModel() {

    val users =
        MutableLiveData<List<User>>()// I've created an object of livedata to send data somewhere

    fun fetchUsers() {
        viewModelScope.launch {// or simply runIO
            val response = withContext(Dispatchers.IO) { GithubRepository.getUsers() }
            if (response.isSuccessful) {
                response.body()?.let {
                    users.postValue(it) // to send data. Here we used poseValue and not value(users.value =it)
                    // because value does everything on the Main thread. But here, we're using default thread. So, in order
                    // to work either on main or IO thread, we use postValue
                }
            }
        }
    }

    // This function return an object of liveData
    fun searchUsers(name: String) =
        liveData(Dispatchers.IO) {// searchUsers() function is returning an object of LiveData.
            val response = withContext(Dispatchers.IO) { GithubRepository.searchUsers(name) }
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it.items!!)// whenever network request is completed, you'll be getting list of items
                }
            }
        }

    /** Extension function for [ViewModel] scope */
    fun ViewModel.runIO(
        dispatchers: CoroutineDispatcher = Dispatchers.IO,
        function: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(dispatchers) {
            function()
        }
    }

}
