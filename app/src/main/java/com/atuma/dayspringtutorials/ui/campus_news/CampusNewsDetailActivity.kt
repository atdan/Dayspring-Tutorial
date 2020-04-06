package com.atuma.dayspringtutorials.ui.campus_news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atuma.dayspringtutorials.R

class CampusNewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campus_news_detail)
    }

    companion object {

        private const val TAG = "CampusNewsDetail"

        const val KEY_CAMPUS_NEWS_ID = "key_campus_news_id"
    }
}
