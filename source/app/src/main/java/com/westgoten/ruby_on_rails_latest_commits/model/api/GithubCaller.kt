package com.westgoten.ruby_on_rails_latest_commits.model.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.westgoten.ruby_on_rails_latest_commits.model.CommitMetadata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubCaller {
    private var service: GithubService? = null

    fun getCommits(owner: String, repo: String): LiveData<List<CommitMetadata>?> {
        if (service == null)
            createService()
        val observableCommitList = MutableLiveData<List<CommitMetadata>>()
        val call = service?.getCommits(owner, repo)
        startRequest(call, observableCommitList)
        return observableCommitList
    }

    private fun createService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(GithubService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(GithubService::class.java)
    }

    private fun startRequest(call: Call<List<CommitMetadata>>?,
                             observableCommitList: MutableLiveData<List<CommitMetadata>>) {
        call?.enqueue(object: Callback<List<CommitMetadata>> {
            override fun onFailure(call: Call<List<CommitMetadata>>, t: Throwable) {
                Log.e("ApiCaller", "Failed request: ${t.message}")
            }

            override fun onResponse(call: Call<List<CommitMetadata>>, response: Response<List<CommitMetadata>>) {
                if (response.isSuccessful)
                    observableCommitList.value = response.body()
                else
                    Log.e("ApiCaller", "Failed request: ${response.code()} - ${response.message()}")
            }
        })
    }
}