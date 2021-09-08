package com.proway.coroutines.repository

import com.proway.coroutines.model.GithubRepositoryResponse
import com.proway.coroutines.model.User
import com.proway.coroutines.service.GithubService
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject

class GithubRepository @Inject constructor(private val services: GithubService) {

    suspend fun getRepositories(): Deferred<GithubRepositoryResponse?> {
        return CoroutineScope(Dispatchers.Default).async {
            val response =
                services.fetchRepositories(language = "language:Kotlin", page = 1, sort = "stars")
                processData(response)
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getUsers(): List<User>? {
        return withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
            val response = services.fetchUsers()
                processData(response)
        }
    }
}