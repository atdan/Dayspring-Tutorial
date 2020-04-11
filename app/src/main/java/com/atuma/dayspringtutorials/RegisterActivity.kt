package com.atuma.dayspringtutorials

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.atuma.dayspringtutorials.util.Connectivity
import com.atuma.dayspringtutorials.util.UserSession
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar
        title = "Credentials"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        auth = FirebaseAuth.getInstance()

        pref = getSharedPreferences(USER_PREF_NAME, Context.MODE_PRIVATE)
        editor = pref.edit()

        val userHashMap = userDetails

        sign_up_button.setOnClickListener {

            if (Connectivity.isConnected(this)){

                Log.d(TAG, "email of user: $userHashMap")
                if (password.editText?.text.toString().length > 5){
                    sign_up_button.startAnimation()
                    auth.createUserWithEmailAndPassword(userHashMap[FIELD_EMAIL]!!,
                        password.editText?.text.toString()).addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful){
                            Log.d(TAG, "Successfully Registered user: ${auth.currentUser?.uid}")
                            sign_up_button.stopAnimation()
                            val intent = Intent(this, HomeActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

                            startActivity(intent)
                            finish()
                        }else{
                            sign_up_button.stopAnimation()
                            sign_up_button.dispose()
                            Log.d(TAG, "Register was not successful Exception: ${task.exception}")
                            Log.d(TAG, "Register was not successful Result: ${task.result}")

                        }
                    })
                }else{
                    sign_up_button.stopAnimation()
                    password.editText?.error = "Password should be at least 6 characters"
                }

            }else{
                val snackbar = Snackbar.make(it, "Check your Internet connection", Snackbar.LENGTH_SHORT)
                snackbar.setTextColor(resources.getColor(R.color.colorPrimary))
                snackbar.setBackgroundTint(resources.getColor(R.color.white))
                snackbar.show()
            }

        }

    }



    private val userDetails: java.util.HashMap<String, String?>
        get() {
            val user = java.util.HashMap<String, String?>()
            user[CreateAccountActivity.FIELD_SURNAME] = pref.getString(UserSession.FIELD_SURNAME, null)
            user[CreateAccountActivity.FIELD_FIRST_NAME] = pref.getString(UserSession.FIELD_FIRST_NAME, null)
            user[CreateAccountActivity.FIELD_DEPARTMENT] = pref.getString(UserSession.FIELD_DEPARTMENT, null)
            user[CreateAccountActivity.FIELD_EMAIL] = pref.getString(UserSession.FIELD_EMAIL, null)
            user[CreateAccountActivity.FIELD_MATRIC] = pref.getString(UserSession.FIELD_MATRIC, null)
            user[CreateAccountActivity.FIELD_FACULTY] = pref.getString(UserSession.FIELD_FACULTY, null)
            user[CreateAccountActivity.FIELD_PHONE] = pref.getString(UserSession.FIELD_PHONE, null)
            user[CreateAccountActivity.FIELD_IMAGE] = pref.getString(UserSession.FIELD_IMAGE, null)
            user[CreateAccountActivity.FIELD_FACULTY_POSITION] = pref.getInt(CreateAccountActivity.FIELD_FACULTY_POSITION, 0).toString()
            user[CreateAccountActivity.FIELD_DEPARTMENT_POSITION] = pref.getInt(
                CreateAccountActivity.FIELD_DEPARTMENT_POSITION, 0).toString()
            return user
        }

    override fun onSupportNavigateUp(): Boolean {
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)

        return super.onSupportNavigateUp()

    }

    override fun onBackPressed() {
        super.onBackPressed()

        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)

    }

    companion object {

        // Sharedpref file name
        private const val PREF_NAME = "UserSessionPref"

        // First time logic Check
        const val FIRST_TIME = "firsttime"

        // All Shared Preferences Keys
        private const val IS_LOGIN = "IsLoggedIn"
        // check first time app launch
        const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

        private const val USER_PREF_NAME = "saveUserPref"

        const val FIELD_SURNAME = "surname"
        const val FIELD_FIRST_NAME = "firstname"
        const val FIELD_EMAIL = "email"
        const val FIELD_FACULTY_POSITION = "faculty-position"
        const val FIELD_DEPARTMENT_POSITION = "department-position"


        const val FIELD_PHONE = "phone"
        const val FIELD_MATRIC = "matric"
        const val FIELD_DEPARTMENT = "department"
        const val FIELD_IMAGE = "image"
        const val FIELD_FACULTY = "faculty"
        const val COLLECTION_USERS = "users"


        const val TAG = "RegisterActivity"
        const val FACULTY_ADMINISTRATION = "Administration"
        const val FACULTY_AGRICULTURE= "Agriculture"
        const val FACULTY_ARTS = "Arts"
        const val FACULTY_EDUCATION = "Education"
        const val FACULTY_EDM = "EDM"
        const val FACULTY_MBBS = "MBBS"
        const val FACULTY_CLINICAL_SCIENCES = "Clinical-Sciences"
        const val FACULTY_DENTISTRY = "Dentistry"
        const val FACULTY_LAW = "Law"
        const val FACULTY_PHARMACY = "Pharmacy"
        const val FACULTY_SCIENCES = "Sciences"
        const val FACULTY_SOCIAL_SCIENCES = "Social-Sciences"
        const val FACULTY_TECHNOLOGY = "Technology"
        const val FACULTY_DEFAULT = "Default"




    }

}
