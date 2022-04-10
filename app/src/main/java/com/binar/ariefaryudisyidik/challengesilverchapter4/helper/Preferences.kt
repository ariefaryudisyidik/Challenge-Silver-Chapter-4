package com.binar.ariefaryudisyidik.challengesilverchapter4.helper

import android.content.Context
import android.preference.PreferenceManager


class Preferences {
    /** Pendeklarasian key-data berupa String,  sebagai wadah penyimpanan data.
    Jadi setiap data mempunyai key yang berbeda satu sama lain  */
    companion object {
        const val EMAIL = "email"
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val USER_LOGIN = "user_login"
        const val LOGIN_STATUS = "login_status"
    }

    /** Pendlakarasian Shared Preferences yang berdasarkan paramater context  */
    private fun getSharedPreference(context: Context) =
        PreferenceManager.getDefaultSharedPreferences(context)

    /** Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key isi KEY_USERNAME_TEREGISTER dengan parameter email  */
    fun setRegisteredUsername(context: Context, username: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(USERNAME, username)
        editor.apply()
    }

    /** Mengembalikan nilai dari key KEY_USERNAME_TEREGISTER berupa String  */
    fun getRegisteredUsername(context: Context) =
        getSharedPreference(context).getString(USERNAME, "")

    /** Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key isi KEY_EMAIL_TEREGISTER dengan parameter email  */
    fun setRegisteredEmail(context: Context, email: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(EMAIL, email)
        editor.apply()
    }

    /** Mengembalikan nilai dari key KEY_EMAIL_TEREGISTER berupa String  */
    fun getRegisteredEmail(context: Context) =
        getSharedPreference(context).getString(EMAIL, "")

    /** Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key KEY_PASS_TEREGISTER dengan parameter password  */
    fun setRegisteredPassword(context: Context, password: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(PASSWORD, password)
        editor.apply()
    }

    /** Mengembalikan nilai dari key KEY_PASS_TEREGISTER berupa String  */
    fun getRegisteredPassword(context: Context) =
        getSharedPreference(context).getString(PASSWORD, "")

    /** Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key KEY_USERNAME_SEDANG_LOGIN dengan parameter username  */
    fun setLoggedInUser(context: Context, username: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(USER_LOGIN, username)
        editor.apply()
    }

    /** Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String  */
    fun getLoggedInUser(context: Context) =
        getSharedPreference(context).getString(USER_LOGIN, "")

    /** Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key KEY_STATUS_SEDANG_LOGIN dengan parameter status  */
    fun setLoggedInStatus(context: Context, status: Boolean) {
        val editor = getSharedPreference(context).edit()
        editor.putBoolean(LOGIN_STATUS, status)
        editor.apply()
    }

    /** Mengembalikan nilai dari key KEY_STATUS_SEDANG_LOGIN berupa boolean  */
    fun getLoggedInStatus(context: Context) =
        getSharedPreference(context).getBoolean(LOGIN_STATUS, false)

    /** Deklarasi Edit Preferences dan menghapus data, sehingga menjadikannya bernilai default
     * khusus data yang memiliki key KEY_USERNAME_SEDANG_LOGIN dan KEY_STATUS_SEDANG_LOGIN  */
    fun clearLoggedInUser(context: Context) {
        val editor = getSharedPreference(context).edit()
        editor.remove(USER_LOGIN)
        editor.remove(LOGIN_STATUS)
        editor.apply()
    }
}