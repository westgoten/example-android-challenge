package com.westgoten.ruby_on_rails_latest_commits.model.api

import com.westgoten.ruby_on_rails_latest_commits.model.CommitMetadata
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubService {
    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val HEADER_ACCEPT_VERSION = "Accept: application/vnd.github.v3+json"
    }

    @Headers(HEADER_ACCEPT_VERSION)
    @GET("repos/{owner}/{repo}/commits")
    fun getCommits(@Path("owner") owner: String, @Path("repo") repo: String): Call<List<CommitMetadata>>
}