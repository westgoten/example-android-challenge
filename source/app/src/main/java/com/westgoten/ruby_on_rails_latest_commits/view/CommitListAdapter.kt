package com.westgoten.ruby_on_rails_latest_commits.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.westgoten.ruby_on_rails_latest_commits.model.CommitMetadata
import com.westgoten.ruby_on_rails_latest_commits.R

class CommitListAdapter(observableCommitList: LiveData<List<CommitMetadata>?>?,
                        lifecycleOwner: AppCompatActivity) :
    RecyclerView.Adapter<CommitListAdapter.CommitListViewHolder>() {

    private var commitList: List<CommitMetadata>? = null
    private val activity = lifecycleOwner

    init {
        observableCommitList?.observe(lifecycleOwner, Observer {
            commitList = it
            notifyDataSetChanged()
        })
    }

    class CommitListViewHolder(val item: LinearLayout,
                               val authorName: TextView,
                               val commitHash: TextView,
                               val commitMessage: TextView) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitListViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_commit_list,
            parent, false) as LinearLayout
        val authorName: TextView = item.findViewById(R.id.commit_author_name)
        val commitHash: TextView = item.findViewById(R.id.commit_hash)
        val commitMessage: TextView = item.findViewById(R.id.commit_message)

        return CommitListViewHolder(item, authorName, commitHash, commitMessage)
    }

    override fun getItemCount(): Int {
        return commitList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CommitListViewHolder, position: Int) {
        val commitMetadata = commitList?.get(position)
        val commit = commitMetadata?.commit
        val author = commit?.author

        holder.authorName.text = author?.name
        holder.commitHash.text = String.format(activity.getString(R.string.commit_hash_format),
            commitMetadata?.sha ?: "")
        holder.commitMessage.text = commit?.message
    }
}