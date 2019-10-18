package com.westgoten.ruby_on_rails_latest_commits.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.westgoten.ruby_on_rails_latest_commits.model.CommitMetadata
import com.westgoten.ruby_on_rails_latest_commits.model.api.GithubCaller

class CommitsViewModel : ViewModel() {
    var observableCommitMetadataList: LiveData<List<CommitMetadata>?>? = null
        private set

    fun fetchCommitsFromServer(owner: String, repo: String) {
        observableCommitMetadataList = GithubCaller.getCommits(owner, repo)
    }
}