package com.proway.coroutines.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proway.coroutines.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: GithubRepository): ViewModel() {

    fun getRepositories() {
        viewModelScope.launch {
            val responseModel = repository.getRepositories().await()
            println(responseModel)
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            val users = repository.getUsers()
            println(users)
        }
    }
}