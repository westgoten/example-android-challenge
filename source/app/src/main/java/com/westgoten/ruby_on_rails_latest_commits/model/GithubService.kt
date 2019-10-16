package com.westgoten.ruby_on_rails_latest_commits.model

import retrofit2.http.GET
import retrofit2.Call

interface GithubService {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("repos/rails/rails/commits")
    fun getCommits() : Call<List<CommitMetadata>>
}