package com.github.islamkhsh.movie_app.data

import com.github.islamkhsh.movie_app.data.database.DatabaseHelper
import com.github.islamkhsh.movie_app.data.network.NetworkHelper
import com.github.islamkhsh.movie_app.data.preferences.PreferenceHelper

interface RepositoryHelper : NetworkHelper, PreferenceHelper, DatabaseHelper