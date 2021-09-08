package com.proway.coroutines.service

import com.proway.coroutines.model.GithubRepositoryResponse
import com.proway.coroutines.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    // Usamos os @Query para deixar os parametros dinamicos
    @GET("/search/repositories")
    suspend fun fetchRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Response<GithubRepositoryResponse>

    @GET("/users")
    suspend fun fetchUsers(): Response<List<User>>
}