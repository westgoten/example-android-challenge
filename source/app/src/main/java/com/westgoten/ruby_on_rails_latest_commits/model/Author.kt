package com.westgoten.ruby_on_rails_latest_commits.model

import java.util.*

const val DATE_FORMAT = "YYYY-MM-DDTHH:MM:SSZ"

data class Author(
    var name : String? = null,
    var email : String? = null,
    var date : Date? = null
)