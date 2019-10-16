package com.westgoten.ruby_on_rails_latest_commits.model

data class Commit(
    var author : Author? = null,
    var message : String? = null
)