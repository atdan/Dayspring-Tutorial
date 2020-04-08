package com.atuma.dayspringtutorials.util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.atuma.dayspringtutorials.ui.login.LoginActivity
import java.util.HashMap

class UserSession(internal var context: Context){
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    var isFirstTimeLaunch: Boolean?
        get() = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(value){
            editor.putBoolean(FIRST_TIME, value!!)
            editor.commit()
        }

    var firstTime: Boolean?
        get() = pref.getBoolean(FIRST_TIME, true)
        set(n) {
            editor.putBoolean(FIRST_TIME, n!!)
            editor.commit()
        }

    /**
     * Create Login Session
     */
    fun createLoginSession(user: HashMap<String, String?>){
        editor.putString(FIELD_SURNAME, user[FIELD_SURNAME])
        editor.putString(FIELD_FIRST_NAME, user[FIELD_FIRST_NAME])
        editor.putString(FIELD_DEPARTMENT, user[FIELD_DEPARTMENT])
        editor.putString(FIELD_EMAIL, user[FIELD_EMAIL])
        editor.putString(FIELD_MATRIC, user[FIELD_MATRIC])
        editor.putString(FIELD_FACULTY, user[FIELD_FACULTY])
        editor.putString(FIELD_PHONE, user[FIELD_PHONE])
        editor.putString(FIELD_IMAGE, user[FIELD_IMAGE])
        editor.commit()
    }
    /**
     * Check Login
     */
    fun checkLogin(){
        if (!this.isLoggedIn){
           // not logged in
            val i = Intent(context, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }
    }

    /**
     * Clear session details
     */
    fun logoutUser() {
        // Clearing all data from Shared Preferences
        editor.putBoolean(IS_LOGIN, false)
        editor.commit()

        // After logout redirect user to Login Activity
        val i = Intent(context, LoginActivity::class.java)

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // Add new Flag to start new Activity
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        // Staring Login Activity
        context.startActivity(i)
    }

    /**
     * Get stored session data
     */
    val userDetails: HashMap<String, String?>
        get() {
            val user = HashMap<String, String?>()
            user[FIELD_SURNAME] = pref.getString(FIELD_SURNAME, null)
            user[FIELD_FIRST_NAME] = pref.getString(FIELD_FIRST_NAME, null)
            user[FIELD_DEPARTMENT] = pref.getString(FIELD_DEPARTMENT, null)
            user[FIELD_EMAIL] = pref.getString(FIELD_EMAIL, null)
            user[FIELD_MATRIC] = pref.getString(FIELD_MATRIC, null)
            user[FIELD_FACULTY] = pref.getString(FIELD_FACULTY, null)
            user[FIELD_PHONE] = pref.getString(FIELD_PHONE, null)
            user[FIELD_IMAGE] = pref.getString(FIELD_IMAGE, null)
            return user
        }

    /**
     * Quick check for login
     */
    // Get Login State
    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    companion object {

        // Sharedpref file name
        private const val PREF_NAME = "UserSessionPref"

        // First time logic Check
        const val FIRST_TIME = "firsttime"

        // All Shared Preferences Keys
        private const val IS_LOGIN = "IsLoggedIn"
        // check first time app launch
        const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

        const val FIELD_SURNAME = "surname"
        const val FIELD_FIRST_NAME = "firstname"
        const val FIELD_EMAIL = "email"
        const val FIELD_PHONE = "phone"
        const val FIELD_MATRIC = "matric"
        const val FIELD_DEPARTMENT = "department"
        const val FIELD_IMAGE = "image"
        const val FIELD_FACULTY = "faculty"
    }
}