package com.atuma.dayspringtutorials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.atuma.dayspringtutorials.util.UserSession
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var prefManager: UserSession? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        auth = FirebaseAuth.getInstance()
//
//        if(auth.currentUser == null){
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }else{
//            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
//        }
        setContentView(R.layout.activity_main)

        prefManager = UserSession(this)
        if (prefManager?.isFirstTimeLaunch!!){
            prefManager!!.checkLogin()
            finish()
        }

        home_button.setOnClickListener(View.OnClickListener {
            val i = Intent(this, HomeActivity::class.java);
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)

        })
    }

//    fun setSplashy(){
//        Splashy(this) 		 // For JAVA : new Splashy(this)
//            .setLogo(R.drawable.splashy)
//            .setTitle("Splashy")
//            .setTitleColor("#FFFFFF")
//            .setSubTitle("Splash screen made easy")
//            .setProgressColor(R.color.white)
////            .setBackgroundResource(R)
//            .setFullScreen(true)
//            .setTime(5000)
//            .show()
//    }
}
