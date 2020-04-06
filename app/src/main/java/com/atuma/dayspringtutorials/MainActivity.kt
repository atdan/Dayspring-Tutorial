package com.atuma.dayspringtutorials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home_button.setOnClickListener(View.OnClickListener {
            val i = Intent(this, HomeActivity::class.java);
            startActivity(i)
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
