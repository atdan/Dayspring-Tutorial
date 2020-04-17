package com.atuma.dayspringtutorials.ui.courses.partOne

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.atuma.dayspringtutorials.R

class CoursesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chm101)

        val intent = intent
        COURSE_SELECTED = intent.getStringExtra(
            COURSE_CODE
        )


    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    companion object{
        const val COURSE_CODE = "COURSE_CODE"
        const val TAG = "CHM101 Activity"


        var COURSE_SELECTED: String? = null



    }
}
