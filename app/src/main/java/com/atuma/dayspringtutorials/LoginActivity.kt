package com.atuma.dayspringtutorials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailString: String

    private lateinit var passwordString: String

    private lateinit var email: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        auth = FirebaseAuth.getInstance()

        email = findViewById<TextInputLayout>(R.id.email)

        login.setOnClickListener {
            if (checkEmail()){
                if (checkPassword()){

                    auth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success")
                                val user = auth.currentUser
                                val intent = Intent(this, HomeActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                                // ...
                            }

                            // ...
                        }
                }
            }
        }

        create_account_text.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
//        checkEmail()
//        checkPassword()
    }

    private fun checkPassword(): Boolean {
        if (TextUtils.isEmpty(password.editText?.text.toString().trim())){
            password.error = "Enter your Password"
            return false
        }else if (password.editText?.text.toString().trim().length < 6){
            password.error = "Incorrect Password"
            return false
        }else{

            passwordString = password.editText?.text.toString().trim()
            return true
        }
    }

    private fun checkEmail(): Boolean {
        return if (TextUtils.isEmpty(email.editText?.text.toString().trim()) ){
            email.error = "enter your email"
            false
        }else{
            emailString = email.editText?.text.toString().trim()
            true
        }
    }

    companion object{
        const val TAG = "LoginActivity"

    }
}
