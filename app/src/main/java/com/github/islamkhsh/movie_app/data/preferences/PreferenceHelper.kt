package com.github.islamkhsh.movie_app.data.preferences



interface PreferenceHelper {

    /**
     * use this method to change user language
     *
     * @param language the language code (0 for en and 1 for arabic)
     */
    fun setUserLanguage(language: Int)

    /**
     * use this method to get the stored user language code or -1 if no language stored
     */
    fun getUserLanguage(): Int
}