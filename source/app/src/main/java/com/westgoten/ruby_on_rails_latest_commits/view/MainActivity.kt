package com.westgoten.ruby_on_rails_latest_commits.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.westgoten.ruby_on_rails_latest_commits.R
import com.westgoten.ruby_on_rails_latest_commits.viewmodel.CommitsViewModel

class MainActivity : AppCompatActivity() {
    private val REPOSITORY_OWNER = "rails"
    private val REPOSITORY_NAME = "rails"
    var viewModel: CommitsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            CommitsViewModel::class.java)

        if (savedInstanceState == null)
            viewModel?.fetchCommitsFromServer(REPOSITORY_OWNER, REPOSITORY_NAME)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.commit_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val adapter = CommitListAdapter(viewModel?.observableCommitMetadataList, this)
        recyclerView.adapter = adapter
    }
}
